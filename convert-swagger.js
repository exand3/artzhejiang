const fs = require('fs');
const path = require('path');
const readline = require('readline');

// 统计信息
let stats = {
    totalFiles: 0,
    processedFiles: 0,
    modifiedFiles: 0,
    errorFiles: 0
};

// 命令行参数
const args = process.argv.slice(2);
const config = {
    srcDir: path.join(__dirname, 'src', 'main', 'java'),
    // createBackup: true,
    verbose: false
};

// 解析命令行参数
function parseArgs() {
    args.forEach((arg, index) => {
        if (arg === '--dir' && args[index + 1]) {
            config.srcDir = path.resolve(args[index + 1]);
        // } else if (arg === '--no-backup') {
            // config.createBackup = false;
        } else if (arg === '--verbose') {
            config.verbose = true;
        } else if (arg === '--help') {
            console.log('Usage: node convert-swagger.js [options]');
            console.log('Options:');
            console.log('  --dir <path>     Specify source directory (default: src/main/java)');
            console.log('  --no-backup      Skip creating backup files');
            console.log('  --verbose        Show detailed output');
            console.log('  --help           Show this help message');
            process.exit(0);
        }
    });
}

// 创建备份文件
// function createBackup(filePath) {
//     try {
//         const backupPath = filePath + '.bak';
//         fs.copyFileSync(filePath, backupPath);
//         if (config.verbose) {
//             console.log(`Backup created: ${backupPath}`);
//         }
//     } catch (error) {
//         console.error(`Failed to create backup for ${filePath}:`, error.message);
//     }
// }

// 遍历目录并处理所有Java文件
function processDirectory(dir) {
    try {
        const files = fs.readdirSync(dir);
        
        files.forEach(file => {
            const filePath = path.join(dir, file);
            try {
                const stat = fs.statSync(filePath);
                
                if (stat.isDirectory()) {
                    processDirectory(filePath);
                } else if (stat.isFile() && file.endsWith('.java')) {
                    stats.totalFiles++;
                    processJavaFile(filePath);
                }
            } catch (error) {
                console.error(`Error processing ${filePath}:`, error.message);
                stats.errorFiles++;
            }
        });
    } catch (error) {
        console.error(`Error reading directory ${dir}:`, error.message);
        stats.errorFiles++;
    }
}

// 处理单个Java文件
function processJavaFile(filePath) {
    try {
        if (config.verbose) {
            console.log(`Processing file: ${filePath}`);
        }
        
        // 读取文件内容
        let content = fs.readFileSync(filePath, 'utf8');
        let originalContent = content;
        
        // 替换导入语句
        content = replaceImportStatements(content);
        
        // 替换注解
        content = replaceAnnotations(content);
        
        // 检查文件是否被修改
        if (content !== originalContent) {
            // 创建备份
            // if (config.createBackup) {
            //     createBackup(filePath);
            // }
            
            // 写回文件
            fs.writeFileSync(filePath, content, 'utf8');
            stats.modifiedFiles++;
            
            if (config.verbose) {
                console.log(`Modified: ${filePath}`);
            }
        }
        
        stats.processedFiles++;
        showProgress();
    } catch (error) {
        console.error(`Error processing ${filePath}:`, error.message);
        stats.errorFiles++;
    }
}

// 替换导入语句
function replaceImportStatements(content) {
    const importReplacements = {
        'import io.swagger.annotations.Api;': 'import io.swagger.v3.oas.annotations.tags.Tag;',
        'import io.swagger.annotations.ApiOperation;': 'import io.swagger.v3.oas.annotations.Operation;',
        'import io.swagger.annotations.ApiParam;': 'import io.swagger.v3.oas.annotations.Parameter;',
        'import io.swagger.annotations.ApiModel;': 'import io.swagger.v3.oas.annotations.media.Schema;',
        'import io.swagger.annotations.ApiModelProperty;': 'import io.swagger.v3.oas.annotations.media.Schema;',
        'import io.swagger.annotations.ApiIgnore;': 'import io.swagger.v3.oas.annotations.Hidden;',
        'import io.swagger.annotations.ApiResponse;': 'import io.swagger.v3.oas.annotations.responses.ApiResponse;',
        'import io.swagger.annotations.ApiResponses;': 'import io.swagger.v3.oas.annotations.responses.ApiResponses;',
        'import io.swagger.annotations.ApiImplicitParam;': 'import io.swagger.v3.oas.annotations.Parameter;',
        'import io.swagger.annotations.ApiImplicitParams;': 'import io.swagger.annotations.ApiImplicitParams;' // 注意：这个没有直接的OpenAPI对应
    };
    
    for (const [oldImport, newImport] of Object.entries(importReplacements)) {
        // 转义正则表达式特殊字符
        const escapedOldImport = oldImport.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
        content = content.replace(new RegExp(escapedOldImport, 'g'), newImport);
    }
    
    return content;
}

// 替换注解
function replaceAnnotations(content) {
    // 优化正则表达式，使其更灵活地匹配不同格式的注解
    
    // @Api 注解 - 支持多种格式
    content = content.replace(
        /@Api\(\s*(tags\s*=\s*)?(["'])(.*?)\2\s*(,\s*value\s*=\s*["'].*?["']\s*)?\)/g,
        '@Tag(name = "$3")'
    );
    
    // @ApiOperation 注解 - 支持不同的参数顺序和格式
    content = content.replace(
        /@ApiOperation\(\s*value\s*=\s*(["'])(.*?)\1\s*(,\s*notes\s*=\s*(["'])(.*?)\4\s*)?\)/g,
        (match, p1, p2, p3, p4, p5) => {
            return p5 ? `@Operation(summary = "${p2}", description = "${p5}")` : `@Operation(summary = "${p2}")`;
        }
    );
    
    // 简化版的 @ApiOperation
    content = content.replace(
        /@ApiOperation\(\s*(["'])(.*?)\1\s*\)/g,
        '@Operation(summary = "$2")'
    );
    
    // @ApiParam 注解 - 支持更多参数
    content = content.replace(
        /@ApiParam\(\s*value\s*=\s*(["'])(.*?)\1\s*(,\s*(required|name|example|defaultValue)\s*=\s*[^,)]+\s*)*\)/g,
        (match, p1, p2, p3) => {
            let result = `@Parameter(description = "${p2}"`;
            if (p3) {
                // 处理其他参数 - 这里可以根据需要添加更多的参数映射
                if (p3.includes('required = true')) {
                    result += ', required = true';
                }
                if (p3.includes('name =')) {
                    const nameMatch = p3.match(/name\s*=\s*(["'])(.*?)\1/);
                    if (nameMatch) {
                        result += `, name = "${nameMatch[2]}"`;
                    }
                }
            }
            result += ')';
            return result;
        }
    );
    
    // 简化版的 @ApiParam
    content = content.replace(
        /@ApiParam\(\s*(["'])(.*?)\1\s*\)/g,
        '@Parameter(description = "$2")'
    );
    
    // @ApiModel 注解
    content = content.replace(
        /@ApiModel\(\s*value\s*=\s*(["'])(.*?)\1\s*(,\s*description\s*=\s*(["'])(.*?)\4\s*)?\)/g,
        (match, p1, p2, p3, p4, p5) => {
            return p5 ? `@Schema(name = "${p2}", description = "${p5}")` : `@Schema(name = "${p2}")`;
        }
    );
    
    // @ApiModelProperty 注解
    content = content.replace(
        /@ApiModelProperty\(\s*value\s*=\s*(["'])(.*?)\1\s*(,\s*(required|example|allowableValues|access|hidden)\s*=\s*[^,)]+\s*)*\)/g,
        (match, p1, p2, p3) => {
            let result = `@Schema(description = "${p2}"`;
            if (p3) {
                // 处理其他参数
                if (p3.includes('required = true')) {
                    result += ', required = true';
                }
                if (p3.includes('hidden = true')) {
                    result += ', hidden = true';
                }
            }
            result += ')';
            return result;
        }
    );
    
    // @ApiIgnore 注解
    content = content.replace(/@ApiIgnore\b/g, '@Hidden');
    
    // @ApiResponse 和 @ApiResponses 注解
    content = content.replace(/@ApiResponse\b/g, '@ApiResponse'); // 保持名称一致
    content = content.replace(/@ApiResponses\b/g, '@ApiResponses'); // 保持名称一致
    
    return content;
}

// 显示进度
function showProgress() {
    if (!config.verbose && stats.totalFiles > 0) {
        const percentage = Math.floor((stats.processedFiles / stats.totalFiles) * 100);
        readline.cursorTo(process.stdout, 0);
        process.stdout.write(`Processing... ${percentage}% (${stats.processedFiles}/${stats.totalFiles} files)`);
    }
}

// 显示统计信息
function showStats() {
    console.log('\n\nConversion Statistics:');
    console.log(`- Total files: ${stats.totalFiles}`);
    console.log(`- Processed files: ${stats.processedFiles}`);
    console.log(`- Modified files: ${stats.modifiedFiles}`);
    console.log(`- Files with errors: ${stats.errorFiles}`);
}

// 主函数
function main() {
    // 解析命令行参数
    parseArgs();
    
    console.log('Starting Swagger to OpenAPI conversion...');
    console.log(`Source directory: ${config.srcDir}`);
    // console.log(`Create backups: ${config.createBackup}`);
    console.log(`Verbose mode: ${config.verbose}`);
    console.log('----------------------------------------');
    
    if (!fs.existsSync(config.srcDir)) {
        console.error('Error: Source directory not found:', config.srcDir);
        return;
    }
    
    try {
        // 开始计时
        const startTime = Date.now();
        
        processDirectory(config.srcDir);
        
        // 结束计时
        const endTime = Date.now();
        const duration = (endTime - startTime) / 1000;
        
        showStats();
        console.log(`Conversion completed in ${duration.toFixed(2)} seconds!`);
        
        // if (config.createBackup) {
        //     console.log('Note: Backup files (.bak) were created for modified files.');
        // }
    } catch (error) {
        console.error('Fatal error during conversion:', error.message);
        showStats();
    }
}

// 执行主函数
main();
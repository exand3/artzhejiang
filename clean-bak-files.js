const fs = require('fs');
const path = require('path');
const readline = require('readline');

// 统计信息
let stats = {
    totalFiles: 0,
    deletedFiles: 0,
    errorFiles: 0
};

// 配置
const config = {
    rootDir: path.join(__dirname),
    verbose: false
};

// 遍历目录并删除所有.bak文件
function scanDirectory(dir) {
    try {
        const files = fs.readdirSync(dir);
        
        files.forEach(file => {
            const filePath = path.join(dir, file);
            try {
                const stat = fs.statSync(filePath);
                
                if (stat.isDirectory()) {
                    // 跳过.git目录
                    if (file === '.git') {
                        return;
                    }
                    scanDirectory(filePath);
                } else if (stat.isFile() && file.endsWith('.bak')) {
                    stats.totalFiles++;
                    deleteFile(filePath);
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

// 删除文件
function deleteFile(filePath) {
    try {
        fs.unlinkSync(filePath);
        stats.deletedFiles++;
        
        if (config.verbose) {
            console.log(`Deleted: ${filePath}`);
        }
        
        showProgress();
    } catch (error) {
        console.error(`Failed to delete ${filePath}:`, error.message);
        stats.errorFiles++;
    }
}

// 显示进度
function showProgress() {
    if (!config.verbose && stats.totalFiles > 0) {
        const percentage = Math.floor((stats.deletedFiles / stats.totalFiles) * 100);
        readline.cursorTo(process.stdout, 0);
        process.stdout.write(`Processing... ${percentage}% (${stats.deletedFiles}/${stats.totalFiles} files)`);
    }
}

// 显示统计信息
function showStats() {
    console.log('\n\nCleanup Statistics:');
    console.log(`- Total .bak files found: ${stats.totalFiles}`);
    console.log(`- Successfully deleted: ${stats.deletedFiles}`);
    console.log(`- Failed to delete: ${stats.errorFiles}`);
}

// 主函数
function main() {
    console.log('Starting cleanup of .bak files...');
    console.log(`Scanning directory: ${config.rootDir}`);
    console.log('----------------------------------------');
    
    if (!fs.existsSync(config.rootDir)) {
        console.error('Error: Directory not found:', config.rootDir);
        return;
    }
    
    try {
        // 开始计时
        const startTime = Date.now();
        
        scanDirectory(config.rootDir);
        
        // 结束计时
        const endTime = Date.now();
        const duration = (endTime - startTime) / 1000;
        
        showStats();
        console.log(`Cleanup completed in ${duration.toFixed(2)} seconds!`);
    } catch (error) {
        console.error('Fatal error during cleanup:', error.message);
        showStats();
    }
}

// 执行主函数
main();
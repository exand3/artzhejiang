package cn.com.zhejiangart.util;

import java.util.HashMap;
import java.util.Map;

public class ThreadUtils {

    // 使用ThreadLocal存储每个线程的ID
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    /**
     * 保存ID到当前线程
     * @param key 键名
     * @param id 要保存的ID值
     */
    public static void saveId(String key, Object id) {
        THREAD_LOCAL.get().put(key, id);
    }

    /**
     * 获取当前线程中保存的ID
     * @param key 键名
     * @return 对应的ID值
     */
    public static Object getId(String key) {
        return THREAD_LOCAL.get().get(key);
    }

    /**
     * 获取当前线程中保存的ID（带类型转换）
     * @param key 键名
     * @param clazz 类型class
     * @return 对应的ID值
     */
    public static <T> T getId(String key, Class<T> clazz) {
        return clazz.cast(THREAD_LOCAL.get().get(key));
    }

    /**
     * 移除当前线程中指定的ID
     * @param key 键名
     */
    public static void removeId(String key) {
        THREAD_LOCAL.get().remove(key);
    }

    /**
     * 清除当前线程的所有数据
     */
    public static void clear() {
        THREAD_LOCAL.remove();
    }
}

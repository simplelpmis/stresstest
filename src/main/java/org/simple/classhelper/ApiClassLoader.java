package org.simple.classhelper;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2018/1/11      @Author Simba
 * @Description:
 */
public class ApiClassLoader extends URLClassLoader {

    private String appName;
    private String path;

    public ApiClassLoader(String appName, String path) {
        super(findURLClassPath(new File(path)));
        this.appName = appName;
        this.path = path;
//        return new ApiClassLoader(appName, findURLClassPath(new File(path)));
    }

    private ApiClassLoader(String appName, URL repositories[]) {
        super(repositories);
        this.appName = appName;
    }

    private ApiClassLoader(URL repositories[], ClassLoader parent) {
        super(repositories, parent);
    }

    public String getAppName() {
        return this.appName;
    }

    private static URL[] findURLClassPath(File file) {
        final List<URL> urls = new ArrayList<>();
        try {
//            urls.add(file.toURI().toURL());
            file.listFiles(new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    try {
                        if (pathname.getName().endsWith(".jar")) {
                            urls.add(pathname.toURI().toURL());
                        }
                    } catch (MalformedURLException ex) {
                    }
                    return false;
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return urls.toArray(new URL[urls.size()]);
    }

    @Override
    public String toString() {
        return "ApiClassLoader{" +
                "appName='" + appName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
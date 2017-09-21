package yxinfo.core.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by dy on 2017/6/23.
 */
public class ScanPackageUtil {

    private static final Logger log = LoggerFactory.getLogger( ScanPackageUtil.class );

    /**
     * 扫描指定包下的service
     *
     * @param packageName
     * @return
     */
    public static List<Class> scanService( String packageName ) {
        return scanPackage( packageName, new Filter() {
            @Override
            public boolean pass( String className ) {
                if ( className != null && className.endsWith( "Service" ) ) {
                    return true;
                }
                return false;
            }
        } );
    }

    /**
     * 扫描指定包下的class
     *
     * @param packageName
     * @param filter
     * @return
     */
    public static List<Class> scanPackage( String packageName, Filter filter ) {

        log.info( "Scan package [{}]", packageName );

        String path = packageName.replaceAll( "\\.", "/" );
        Enumeration<URL> urls = null;
        try {
            urls = Thread.currentThread().getContextClassLoader().getResources( path );
        } catch ( IOException e ) {
            log.error( "Scan package rrror, {}", e.getMessage(), e );
            return null;
        }

        List<Class> ret = new ArrayList<Class>();
        while ( urls.hasMoreElements() ) {
            URL url = urls.nextElement();

            List<String> classNames = new ArrayList<String>();
            List<Class> classes = new ArrayList<Class>();
            try {
                if ( url != null && url.toString().startsWith( "file" ) ) {

                    // 扫描文件夹
                    String filePath = URLDecoder.decode( url.getFile(), "utf-8" );
                    log.info( "Scan file path [{}]", filePath );

                    File dir = new File( filePath );
                    List<File> files = new ArrayList<File>();
                    getAllFilesFromDir( dir, files );

                    for ( File file : files ) {
                        String fileName = file.getAbsolutePath();
                        if ( fileName.endsWith( ".class" ) ) {
                            classNames.add( fileName.substring( 8 + fileName.lastIndexOf( "classes" ), fileName.indexOf( ".class" ) ).replaceAll( "\\\\", "." ) );
                        }
                    }

                } else if ( url != null && url.toString().startsWith( "jar" ) ) {

                    // 扫描jar
                    JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                    JarFile jarFile = jarURLConnection.getJarFile();
                    log.info( "Scan jar [{}]", url.getFile() );

                    Enumeration<JarEntry> jarEntries = jarFile.entries();
                    while ( jarEntries.hasMoreElements() ) {
                        String jarEntryName = jarEntries.nextElement().getName();
                        String className = jarEntryName.replace( "/", "." );
                        if ( className != null && className.endsWith( ".class" ) ) {
                            classNames.add( className.substring( 0, className.length() - 6 ) );
                        }
                    }
                }

                // 过滤
                for ( String className : classNames ) {
                    log.info( "Scan class [{}]", className );
                    if ( filter != null ) {
                        if ( filter.pass( className ) ) {
                            classes.add( Class.forName( className ) );
                        }
                    } else {
                        classes.add( Class.forName( className ) );
                    }
                }
                ret.addAll( classes );

            } catch ( Exception e ) {
                log.error( "Scan package rrror, {}", e.getMessage(), e );
            }
        }
        return ret;
    }

    /**
     * 获取目录下所有文件
     *
     * @param file
     * @param files
     */
    private static void getAllFilesFromDir( File file, List<File> files ) {
        if ( file.isDirectory() ) {
            for ( File f : file.listFiles() ) {
                getAllFilesFromDir( f, files );
            }
        } else {
            files.add( file );
        }
    }

    /**
     * 扫描过滤
     */
    interface Filter {
        boolean pass( String className );
    }
}

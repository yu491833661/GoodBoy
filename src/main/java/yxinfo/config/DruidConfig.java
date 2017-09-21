package yxinfo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import yxinfo.core.framework.service.dal.CreateAtPlug;
import yxinfo.core.framework.service.dal.PagePlug;

import java.sql.SQLException;

/**
 * 注解ConfigurationProperties(prefix = "spring.datasource.druid") 可不用在属性上配置value
 *
 * @author jn
 * @date 2017/9/13 10:40
 **/
@Configuration
@EnableTransactionManagement
@MapperScan( basePackages = { "yxinfo.yjh.dao.mapper", "yxinfo.core.service.ou.dao.mapper", "yxinfo.core.service.sms.dao.mapper" } )
public class DruidConfig {

    @Value( "${spring.datasource.druid.url}" )
    private String url;

    @Value( "${spring.datasource.druid.username}" )
    private String username;

    @Value( "${spring.datasource.druid.password}" )
    private String password;

    @Value( "${spring.datasource.druid.initialSize}" )
    private int initialSize;

    @Value( "${spring.datasource.druid.maxActive}" )
    private int maxActive;

    @Value( "${spring.datasource.druid.minIdle}" )
    private int minIdle;

    @Value( "${spring.datasource.druid.maxWait}" )
    private long maxWait;

    @Value( "${spring.datasource.druid.validationQuery}" )
    private String validationQuery;

    @Value( "${spring.datasource.druid.testOnBorrow}" )
    private boolean testOnBorrow;

    @Value( "${spring.datasource.druid.testOnReturn}" )
    private boolean testOnReturn;

    @Value( "${spring.datasource.druid.testWhileIdle}" )
    private boolean testWhileIdle;

    @Value( "${spring.datasource.druid.timeBetweenEvictionRunsMillis}" )
    private long timeBetweenEvictionRunsMillis;

    @Value( "${spring.datasource.druid.minEvictableIdleTimeMillis}" )
    private long minEvictableIdleTimeMillis;

    @Value( "${spring.datasource.druid.removeAbandoned}" )
    private boolean removeAbandoned;

    @Value( "${spring.datasource.druid.removeAbandonedTimeout}" )
    private int removeAbandonedTimeout;

    @Value( "${spring.datasource.druid.logAbandoned}" )
    private boolean logAbandoned;

    @Value( "${spring.datasource.druid.filters}" )
    private String filters;

    @Bean( initMethod = "init", destroyMethod = "close" )
    @Primary
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl( url );
        dataSource.setUsername( username );
        dataSource.setPassword( password );
        dataSource.setInitialSize( initialSize );
        dataSource.setMaxActive( maxActive );
        dataSource.setMinIdle( minIdle );
        dataSource.setMaxWait( maxWait );
        dataSource.setValidationQuery( validationQuery );
        dataSource.setTestOnBorrow( testOnBorrow );
        dataSource.setTestOnReturn( testOnReturn );
        dataSource.setTestWhileIdle( testWhileIdle );
        dataSource.setTimeBetweenEvictionRunsMillis( timeBetweenEvictionRunsMillis );
        dataSource.setMinEvictableIdleTimeMillis( minEvictableIdleTimeMillis );
        dataSource.setRemoveAbandoned( removeAbandoned );
        dataSource.setRemoveAbandonedTimeout( removeAbandonedTimeout );
        dataSource.setLogAbandoned( logAbandoned );
        dataSource.setFilters( filters );
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource( dataSource() );
        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations( resolver.getResources( "classpath*:yxinfo/**/dao/xml/*.xml" ) );
        // 插件
        Interceptor[] interceptors = {new CreateAtPlug(), new PagePlug()};
        sqlSessionFactoryBean.setPlugins( interceptors );
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 事务
     *
     * @return
     * @throws SQLException
     */
    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager( dataSource() );
    }

    /**
     * 监控
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet( new StatViewServlet() );
        reg.addUrlMappings( "/druid/*" );
        // 白名单
//        reg.addInitParameter( "allow", "127.0.0.1" );
        // 黑名单
//        reg.addInitParameter( "deny","" );
        reg.addInitParameter( "loginUsername", "admin" );
        reg.addInitParameter( "loginPassword", "admin" );
        return reg;
    }

    /**
     * 监控
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter( new WebStatFilter() );
        filterRegistrationBean.addUrlPatterns( "/*" );
        filterRegistrationBean.addInitParameter( "exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*" );
        return filterRegistrationBean;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize( int initialSize ) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive( int maxActive ) {
        this.maxActive = maxActive;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle( int minIdle ) {
        this.minIdle = minIdle;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait( long maxWait ) {
        this.maxWait = maxWait;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery( String validationQuery ) {
        this.validationQuery = validationQuery;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow( boolean testOnBorrow ) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn( boolean testOnReturn ) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle( boolean testWhileIdle ) {
        this.testWhileIdle = testWhileIdle;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis( long timeBetweenEvictionRunsMillis ) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis( long minEvictableIdleTimeMillis ) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public boolean isRemoveAbandoned() {
        return removeAbandoned;
    }

    public void setRemoveAbandoned( boolean removeAbandoned ) {
        this.removeAbandoned = removeAbandoned;
    }

    public int getRemoveAbandonedTimeout() {
        return removeAbandonedTimeout;
    }

    public void setRemoveAbandonedTimeout( int removeAbandonedTimeout ) {
        this.removeAbandonedTimeout = removeAbandonedTimeout;
    }

    public boolean isLogAbandoned() {
        return logAbandoned;
    }

    public void setLogAbandoned( boolean logAbandoned ) {
        this.logAbandoned = logAbandoned;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters( String filters ) {
        this.filters = filters;
    }
}

package com.techOps.notificationService.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.util.Properties;


@Configuration
//@PropertySource("classpath:application.properties")
public class SpringEmailConfiguration implements ApplicationContextAware, EnvironmentAware {

    public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";

    private ApplicationContext applicationContext;
    private Environment environment;

    private static final String JAVA_MAIL_FILE = "classpath:application.properties";

    private static final String HOST = "spring.mail.host";
    private static final String PORT = "spring.mail.port";
    private static final String PROTOCOL = "spring.mail.protocol";
    private static final String USERNAME = "spring.mail.username";
    private static final String PASSWORD = "spring.mail.password";

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    /*
     * SPRING + JAVAMAIL: JavaMailSender instance, configured via .properties files.
     */
//    @Bean
    public JavaMailSender mailSender() throws IOException {
//        Properties props = new Properties();
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.ssl.enable", "true");

//        Session session = Session.getInstance(props);


        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Basic mail sender configuration, based on emailconfig.properties
        mailSender.setHost(this.environment.getProperty(HOST));
        mailSender.setPort(Integer.parseInt(this.environment.getProperty(PORT)));
        mailSender.setProtocol(this.environment.getProperty(PROTOCOL));
        mailSender.setUsername(this.environment.getProperty(USERNAME));
        mailSender.setPassword(this.environment.getProperty(PASSWORD));
//        mailSender.setSession(session);

        // JavaMail-specific mail sender configuration, based on javamail.properties
        final Properties javaMailProperties = new Properties();
        javaMailProperties.load(this.applicationContext.getResource(JAVA_MAIL_FILE).getInputStream());
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

    @Bean
    public ResourceBundleMessageSource emailMessageSource() {

        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("mail/MailMessages");

        return messageSource;
    }

//    @Bean
//    public TemplateEngine emailTemplateEngine() {
//
//        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//
//        // resolver for text emails
//        templateEngine.addTemplateResolver(textTemplateResolver());
//
//        // resolver for HTML emails
//        templateEngine.addTemplateResolver(htmlTemplateResolver());
//
//        // resolver for string emails
//        templateEngine.addTemplateResolver(stringTemplateResolver());
//
//        // message source, internationalization specific to emails
//        templateEngine.setMessageSource(emailMessageSource());
//
//        return templateEngine;
//    }
//
//    private ITemplateResolver textTemplateResolver() {
//
//        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//
//        templateResolver.setOrder(Integer.valueOf(1));
//
//        templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
//
//        templateResolver.setPrefix("/mail/");
//
//        templateResolver.setSuffix(".txt");
//
//        templateResolver.setTemplateMode(TemplateMode.TEXT);
//
//        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
//
//        templateResolver.setCacheable(false);
//
//        return templateResolver;
//    }
//
//    private ITemplateResolver htmlTemplateResolver() {
//
//        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//
//        templateResolver.setOrder(Integer.valueOf(2));
//
//        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
//
//        templateResolver.setPrefix("/mail/");
//
//        templateResolver.setSuffix(".html");
//
//        templateResolver.setTemplateMode(TemplateMode.HTML);
//
//        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
//
//        templateResolver.setCacheable(false);
//
//        return templateResolver;
//    }
//
//    private ITemplateResolver stringTemplateResolver() {
//
//        final StringTemplateResolver templateResolver = new StringTemplateResolver();
//
//        templateResolver.setOrder(Integer.valueOf(3));
//
//        // No resolvable pattern, will simply process as a String template everything
//        // not previously matched
//        templateResolver.setTemplateMode("HTML5");
//
//        templateResolver.setCacheable(false);
//
//        return templateResolver;
//    }

}

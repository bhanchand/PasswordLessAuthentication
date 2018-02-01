# PasswordLessAuthentication
Author: bhanchand.prasad@okta.com

This demo explores the capabilities of Okta's platform, specifically in terms of using password less authetication for B2C use cases. It uses the strength of the Okta's API and OAuth implementaion combined with Multifcator Authentication.

In this demonstration, a user is required to register an email address to access a document. For first time user, a pass code is emailed. Upon validation of the passcode, the user is granted access.

An already registered user, can directly access the document without the need for a passcode.

The link to the document requires an access token, which can be revoked as well as be time bound based on the OAUTh configuration on the Okta portal.

It is java SpringBoot WAR application that can be run on a Java Application Server like Tomcat. The Springboot uses Apache Freemarker Template. Styling is done using Bootstrap and Okta styles.

A trial account for Okta is free and can be obtained at 
    https://developer.okta.com/
Configuration of application.properties is required. A API Token and OAuth Client Id and Client Secret are needed.

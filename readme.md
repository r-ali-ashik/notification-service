# notificationService

The notification-service API is a RESTful api and it is responsible for sending the email and sms notification to the end user. This service uses thymeleaf as teh templating engine and allows its client to store html email template in the database and initiation email/sms notification to the end user via api call. 


## Technology Used
- Java 8
- Spring Boot 2.1.8
- Thymeleaf 
- Spring data jpa
- MySQL
- Spring mail

## Overview of the architecture 
- NotificationController exposes the APIs responsible for sending email/sms
- TemplateController exposes the APIs for template related CRUD operations
 
 
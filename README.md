# Introduction 
Servicio que se encarga de las siguientes tareas:

- Validaciones en base de datos PMC
- Validaciones de valores de campos

#Variables de entorno

Para que este microservicio pueda ser ejecutado deber\u00E1n de existir las siguietnes variables de entorno en el SO annfitri\u00F3n donde se ejecute el jar


============== AMBIENTE DE QA ===================

- authenticationdatabaseMongo PMCQA01
- usrMongoCifras pmcmsconsulta
- pwMongoCifras pmcmsconsulta0
- databaseMongo PMCQA01
- portMongo 27017
- hostMongo 172.16.168.94
- portMSValidacionLocal 9011
- fileLogValidacionLocal /home/weblogic/mspmc/logs/mspmc-validacionlocal.log 

============== AMBIENTE DE UAT ===================

- authenticationdatabaseMongo PMCUAT01
- usrMongoCifras pmcmsconsulta
- pwMongoCifras pmcmsconsulta0
- databaseMongo PMCUAT01
- portMongo 27017
- hostMongo 10.100.8.80
- portMSValidacionAlmacenes 9011
- fileLogValidacionAlmacenes /home/weblogic/mspmc/logs/mspmc-validacionlocal.log 

============== AMBIENTE DE PROD ===================

- authenticationdatabaseMongo [pendiente]
- usrMongoCifras [pendiente]
- pwMongoCifras [pendiente]
- databaseMongo [pendiente]
- portMongo [pendiente]
- hostMongo [pendiente]
- portMSValidacionAlmacenes 9011
- fileLogValidacionAlmacenes /home/weblogic/mspmc/logs/mspmc-validacionlocal.log 

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1.	Installation process
2.	Software dependencies
3.	Latest releases
4.	API references

# Build and Test
TODO: Describe and show how to build your code and run the tests. 

# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)
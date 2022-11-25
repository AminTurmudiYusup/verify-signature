# verify-signature
In This Project I describe How to verify signature in springboot using java cryptoghraphy
Prerequisite
1. You understand the concept asymetric encryption
2. You already see my previous tutorial(create private key, public key, signature and verify signature using public key=https://www.youtube.com/watch?v=qTYFRXaphzc)
3. Understand the concept signature
4. Understand how to create spring boot app from scratch
5. Understand how to setting connection from spring boot app to mysql
6. Understand how to add CustomFilter into spring security filter chain
7. Understand how to create error handling on filter level(on security level)
8. Understand how to create request using Postman

In this tutorial i will use this scenario
1. client create public key, private key and signature
2. send public key manually(send by email to server and save to application.properties)
3. save signature, message and use when client send request to server
4. every time client send request to server, include signature and message in header.
5. when request arrive in the server, verify signature using public key.
6. if valid, Dispatch request into controller.
7. to  create public key, private key and signature see my previous tutorial

==SIGNATURE==
oMKEoErLWep5MdO9cpqP3g2Ly86qspsczQem9Iv7RSXvggDrfE3RhOgSwneuxXVZ5F0W5iQm7lm2fvWC1DiFFkv4QzYWyK2MtA+wrPB41Lbay4eEzpPOigSnFBixgkt9xmPate5pgnzsjk1ZMnCd/be8mBtY54sxSXf3MCa3E4D5MUVEKzonKEodzfDkdOw3N2pm1iSarXWxkFHPyJSy/IghG6mSUquV6ma4e14Y4QfKbkl6yzsqeixmMhLiO7sxP8Rfi04VXt3rb0rsZq7KyQLHpXGInOA7FuBGUxj+6wX4sJWEAykDbsm6wuqCtlC8VeIfWe9QafkWJQdUe8Ve4A==
==PUBLIC KEY==
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0t5EkuX0MZOGcCazyYv50ZlOgkPPhy2EJCtVyZSTzok69nvKCq7FFUK8FYoDE/x8czSCUHTQhWMbXVdfYA+eAf4RTXPjCv6ad1tUnVc8mmaLdLkNKGAFXzv1+i2tyU0aP4MZYjQH9tnzJ7I3AgSG/2T2UPN20/MNKHzAkVy1pH+Qvbx3O1DPN3NXPHnvczBkSvCOaErSWUeSKx0Cub5YhW2T4ZgPCdgG40YmCZDmBBKQLbJvtz/5yyKZo7LIY7zyNdWFND1LV9y8caDEP4mdwc3u1DW9cfYX+9WHGbLpaPO2w4KrVocC1Ew45E1v+xBl7akU4lAVrsUx7SF/vjqrpwIDAQAB
==MESSAGE==
MESSAGE


Let's jump right in
1. dependency needed, see my pom.xml
2. see my package structure
3. create Product entity
4. create Product Repository
5. create Product Controller
6. create Signature Service
7. create custom filter
8. create spring security class and add custom filter in security
9. create FilterError for handling error in Filter level
10.create setErrorResponse method inside FilterSignature class
9. try to run app

- this is list of api which secured using spring security
   - http://localhost:8080/product   (GET)
   - http://localhost:8080/product/14(GET)
   - http://localhost:8080/product	 (POST)
   - http://localhost:8080/product	 (PUT)
   - http://localhost:8080/product/16(DELETE)

 Access API without signature and message
 - if client try to access API without signature and message,  
 -show error message forbidden
 Access API using signature and message
 - if client try to access API using valid signature and valid message
 - dispatch request into controller
 - and send response to client


 Success, Happy learning and happy sharing !!!
 You can fork this tutorial in my github

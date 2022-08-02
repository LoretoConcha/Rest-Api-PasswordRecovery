# Rest-Api-PasswordRecovery

RestFul Web Service developed to contain data recovery and modification methods corresponding to recovering a generic company's user password.

----------

INPUTS

All the inputs contain the following JSON format:
```
[
{  
"dtoRequestSetParameters":{  
"dtoBusiness":{  
"entry":""  
}  
}  
}
]
```
dtoRequestSetParameters: Standard object containing the business objects of the request.

dtoBusiness: Business object/objects for the request. These objects will always have names appropriate to the requested service, for example, when requesting information from a customer, this will be called dtoCustomer.

----------

OUTPUTS
All the outputs contain the following JSON format:

```
[
{

"dtoResponseCodeOoutHttp":  {

"code": "200",

"message": "OK",

"description": "OK"

},

"dtoResponseSetResult":  {

"dtoResponseOperationCode":  {

"responseCode": "0",

"message": "Operation executed successfully"

},

"dtoBusiness": { }

}

}
]
```
dtoResponseCodeOoutHttp: Contains the HTTP response codes for the current request.

dtoResponseSetResult: This object contains the result of the execution of the requested service, it can contain more than one object representing such results. These objects will always have names appropriate to the requested service. It also contains the following object that represents the codes corresponding to the result of the operation of the requested service.



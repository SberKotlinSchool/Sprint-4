abstract class ValidationException(message: String) : RuntimeException(message)

class InvalidPhoneNumber : ValidationException("Invalid phone number")

class InvalidFirstName : ValidationException("Invalid first name")

class InvalidLastName : ValidationException("Invalid last name")

class InvalidEmail : ValidationException("Invalid e-mail")

class InvalidInsuranceNumber : ValidationException("Invalid insurance number")
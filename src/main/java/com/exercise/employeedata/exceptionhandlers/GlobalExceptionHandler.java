//package com.exercise.employeedata.exceptionhandlers;
//
//import com.exercise.employeedata.Exceptions.EmployeeNotFoundException;
//import com.exercise.employeedata.Responses.ExceptionResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.TransactionSystemException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.time.LocalDateTime;
//
//// TODO read up on Global Exception Handler
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(EmployeeNotFoundException.class)
//    public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException exception, WebRequest webRequest) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("Employee not found", LocalDateTime.now());
//        exception.printStackTrace();
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest webRequest) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("The given id must not be null", LocalDateTime.now());
//        exception.printStackTrace();
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(TransactionSystemException.class)
//    public ResponseEntity<Object> handleTransactionSystemException(TransactionSystemException exception, WebRequest webRequest) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("All fields must have a value", LocalDateTime.now());
//        exception.printStackTrace();
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }
//
////    @ExceptionHandler(HttpMessageNotReadableException.class)
////    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest webRequest) {
////        ExceptionResponse exceptionResponse = new ExceptionResponse("Body must have content", LocalDateTime.now());
////        exception.printStackTrace();
////        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
////    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//            CustomErrorDetailsResponse customErrorDetailsResponse = new CustomErrorDetailsResponse(
//            new Date(),
//            "From MethodArgumentNotValid in GEH",
//            ex.getMessage());
//            return new ResponseEntity<>(customErrorDetailsResponse, HttpStatus.BAD_REQUEST);
//            }
//}

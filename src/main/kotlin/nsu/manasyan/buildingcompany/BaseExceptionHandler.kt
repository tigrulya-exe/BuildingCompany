package nsu.manasyan.buildingcompany

import nsu.manasyan.buildingcompany.exceptions.NoDataFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class BaseExceptionHandler {
    class ErrorModel(var error: String)

    @ExceptionHandler(NoDataFoundException::class)
    fun handleNotFound(exc: NoDataFoundException): ResponseEntity<ErrorModel> {
        logger().error("NoDataFoundException: '${exc.localizedMessage}'")
        return ResponseEntity(ErrorModel(exc.localizedMessage), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(HttpMessageConversionException::class)
    fun handleConversionError(exc: HttpMessageConversionException) : ResponseEntity<ErrorModel>{
        logger().error("HttpMessageConversionException: '${exc.localizedMessage}'")
        return ResponseEntity(ErrorModel("Wrong data"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleConversionError(exc: IllegalArgumentException) : ResponseEntity<ErrorModel>{
        logger().error("IllegalArgumentException: '${exc.localizedMessage}'")
        return ResponseEntity(ErrorModel(exc.localizedMessage), HttpStatus.BAD_REQUEST)
    }
}
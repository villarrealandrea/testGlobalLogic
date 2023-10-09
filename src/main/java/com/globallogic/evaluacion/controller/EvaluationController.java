package com.globallogic.evaluacion.controller;

import com.globallogic.evaluacion.domain.Error;
import com.globallogic.evaluacion.domain.User;
import com.globallogic.evaluacion.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Slf4j
public class EvaluationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("sign-up")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
        log.info("[START] createUser -> User: {}", user.toString());
        try {
            user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
             User userCreated = userRepository.save(user);

            log.info("[END] createUser -> User.id: {}", userCreated.getId());
            return ResponseEntity.ok().body(userCreated);
       } catch (Throwable th) {
            log.error("[ERROR] -> CreateUser: " + th.getMessage(), th);
            return ResponseEntity.badRequest().body(null);
        }

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<Error> handleConstraintViolationException(DataIntegrityViolationException e) {
        return List.of(new Error("El Usuario ya se encuentra en la base"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<Error> handleMethodViolationException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        List<Error> errores = fieldErrors.stream().map(fieldError -> new Error(fieldError.getDefaultMessage()))
                             .collect(Collectors.toList());
        return errores;
    }
}

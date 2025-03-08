package com.example.springmvcboot.aop;

import java.util.List;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.springmvcboot.model.Alien;

/**
 * Aspect class that handles logging concerns for the AlienRestController.
 * This aspect provides logging functionality before and after method executions,
 * as well as logging for successful completions and exceptions.
 */
@Aspect
@Component
public class LoggingAspect {

  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  /**
   * Logs before the execution of getAllAliens method.
   * This advice runs before the method is called.
   */
  @Before("execution(public * com.example.springmvcboot.controller.AlienRestController.getAllAliens())")
  public void logBefore() {
    logger.info("Before: getAliens method called");
  }

  /**
   * Logs after the execution of getAllAliens method.
   * This advice runs after the method execution, regardless of its outcome (success or exception).
   */
  @After("execution(public * com.example.springmvcboot.controller.AlienRestController.getAllAliens())")
  public void logAfter() {
    logger.info("After (Finally): getAliens method executed - This runs regardless of outcome");
  }

  /**
   * Logs after successful execution of getAllAliens method.
   * This advice runs only if the method executes successfully without throwing any exception.
   *
   * @param result the List of Aliens returned by the getAllAliens method
   */
  @AfterReturning(
    pointcut = "execution(public * com.example.springmvcboot.controller.AlienRestController.getAllAliens())",
    returning = "result"
  )
  public void logAfterReturning(List<Alien> result) {
    logger.info("AfterReturning: getAliens method returned successfully with {} aliens", result.size());
  }

  /**
   * Logs after an exception is thrown from any method in AlienRestController.
   * This advice runs when an exception occurs during method execution.
   *
   * @param ex the Exception that was thrown during method execution
   */
  @AfterThrowing(
    pointcut = "execution(public * com.example.springmvcboot.controller.AlienRestController.*(..))",
    throwing = "ex"
  )
  public void logAfterThrowing(Exception ex) {
    logger.error("AfterThrowing: An exception occurred in AlienRestController: {}", ex.getMessage());
  }

  /**
   * Logs after the execution of any method in AlienRestController.
   * This is a general-purpose advice that runs after any method execution in the controller.
   */
  @After("execution(* com.example.springmvcboot.controller.AlienRestController.*(..))")
  public void logAfterAnyMethod() {
    logger.info("After (Finally): Any method in AlienRestController has completed execution");
  }
}

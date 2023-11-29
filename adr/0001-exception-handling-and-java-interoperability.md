# ADR 0001: Exception Handling and Java Interoperability

## Status

Accepted

## Context

Exception handling in BigBone should consider Java developers as well. While Kotlin is unaware of the concept of checked 
exceptions, this is a concern in the Java world. Today, BigBone throws exception in certain methods which are annotated
with `@Throws`. Annotating methods with `@Throws` leads to the addition of a `throws` declaration to the method
signature. This forces the Java developer to explicitly handle exceptions which can be cumbersome and leads to a lot of
boilerplate code.

## Decision

- All exceptions thrown by BigBone inherit from the `Exception` class.
- Public methods in BigBone must not be annotated using `@Throws` as this forces Java developers to declare exceptions 
  using `throws` or handle them explicitly using `try/catch`.
- Internal private methods in BigBone which throw exceptions shall be annotated with `@Throws` to make BigBone 
  developers more aware of the fact that these methods throw an exception. 
- In general, we should add a `@throws` clause to the KDoc section of a BigBone methods, if it throws exceptions.

## Consequences

- Exception handling for Java developers will become easier as they do not need to explicitly declare or handle 
  exceptions.

package sample.validation;

public interface Validator<T> {

    String validate(T item);
}

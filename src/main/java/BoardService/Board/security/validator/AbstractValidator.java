package BoardService.Board.security.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;



public abstract class AbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void validate(Object target, Errors errors) {
        try {
            Validate((T) target,errors);

        }catch (RuntimeException e){
            throw e;
        }
    }
    protected abstract void Validate(final T dto, final Errors errors);
}
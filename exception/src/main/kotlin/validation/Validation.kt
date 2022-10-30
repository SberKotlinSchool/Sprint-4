package validation

import ErrorCode

interface Validation<E> {
    fun validate(data: E): ErrorCode?
}

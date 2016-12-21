#include <avr/io.h>

.global greaterThan
greaterThan:
  cp r24, r22
  call printRegs
  ret

.global slowMultiply
slowMultiply:
  
  ret

.global slowExponent
slowExponent:
  ret

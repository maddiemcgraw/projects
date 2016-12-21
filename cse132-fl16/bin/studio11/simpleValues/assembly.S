#include <avr/io.h>

#define a r24

; AVR assembly comments start with a semicolon
// (or a double slash, like C)
; Lines without labels should be indented once.

.global giveMeMax
giveMeMax:
ldi r24, 1 // Load the value 1 into register 24
ret

//  Define further "functions" below:
.global giveMeZero
giveMeZero:
rcall printRegs
ldi r24, 0
rcall printRegs
ret

.global addFour
addFour:
ldi r25, 4
add r24, r25
ret

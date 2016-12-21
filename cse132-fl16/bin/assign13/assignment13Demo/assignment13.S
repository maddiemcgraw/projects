//first r16ay in r24
//second in r28
#include <avr/io.h>


.global divide
divide:
  ldi r30, 0
  mov r18, r24
  mov r20, r22
  ldi r22, 1
  ldi r24, 0
while_loop:
  cp r18, r20
  brlo exit_loop
  add r24, r22
  sub r18, r20
  jmp while_loop
exit_loop:
  ret

.global sumArray
sumArray:
  mov r26, r24
  mov r27, r25
  ldi r18, 0 //Pointer location
  ldi r20, 0 //Low bit of sum
  ldi r21, 0 //High bit of sum
  ldi r30, 0 //Register for loading array value
add:
  cp r18, r22 //Compare pointer location with array length
  brge exit_add //If pointer is greater than or equal to length, no more vals to add
  ld r30, X //Load array value to r30
  add r20, r30 //Add array value to sum
  adc r21, r31
  inc r26 //Move to next value in array
  inc r18 //Increase pointer
  jmp add
exit_add:
  mov r24, r20
  mov r25, r21
ret

.global avgArray
avgArray:
  call sumArray
  mov r18, r24
  mov r19, r25
  ldi r21, 0
  mov r20, r22
  ldi r23, 0
  ldi r24, 0
  ldi r25, 0
solve:
  cpc r23, r19
  brlo increase
  cp r18, r22
  brlo exit_avg
increase:
  inc r24
  add r22, r20
  adc r23, r21
  jmp solve
exit_avg:
  ret


.global dot
dot:
  push 28
  push 29
  mov r26, r24 //Array a goes to X
  mov r27, r25
  mov r28, r22 //Array b goes to Y
  mov r29, r23
  ldi r18, 0 //Pointer location
  ldi r22, 0 //r holds array a value
  ldi r30, 0 //r holds array b vlaue
  ldi r24, 0 //r for low byte mult sum
  ldi r25, 0 //r for high byte mult sum
mult_add:
  cp r18, r20
  brge exit_dot
  ld r22, X //loading array value
  ld r30, Y //loading array value
  mul r22, r30
  add r24, r0
  adc r25, r1
  inc r26
  inc r28
  inc r18
  jmp mult_add
exit_dot:
  pop 28
  pop 29
  ret


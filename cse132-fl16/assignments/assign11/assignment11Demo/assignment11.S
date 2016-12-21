#include <avr/io.h>

  .global hasAOne
hasAOne:
  mov r30, r24
  ldi r18, 0 
  ldi r24, 0
  cpse r30, r18
  ldi r24, 1
  ret

  .global intToAscii
intToAscii:
  ldi r18, 48
  add r24, r18
  ret

  .global byteToInt
byteToInt:
  mov r30, r24
  lsr r30
  lsr r30
  lsr r30
  lsr r30
  ldi r18, 0
  cpse r30, r18
  ldi r25, 255
  ret
  
  .global addBytes
addBytes:
  ldi r25, 0
  ldi r23, 0
  ldi r30, 0
  mov r18, r24
  mov r20, r22
  lsr r18
  lsr r18
  lsr r18
  lsr r18
  lsr r18
  lsr r18
  lsr r18
  cpse r18, r30
  ldi r25, 255
  lsr r20
  lsr r20
  lsr r20
  lsr r20
  lsr r20
  lsr r20
  lsr r20
  cpse r20, r30
  ldi r23, 255
  add r24, r22
  adc r25, r23
  ret
    
  .global average
average:
  ldi r25, 0
  ldi r23, 0
  ldi r30, 0
  ldi r31, 255
  mov r18, r24
  mov r20, r22
  lsr r18
  lsr r18
  lsr r18
  lsr r18
  lsr r18
  lsr r18
  lsr r18
  cpse r18, r30
  ldi r25, 255
  lsr r20
  lsr r20
  lsr r20
  lsr r20
  lsr r20
  lsr r20
  lsr r20
  cpse r20, r30
  ldi r23, 255
  add r24, r22
  adc r25, r23
  cpse r25, r31
  lsr r24
  cpse r25, r30
  asr r24
  ret


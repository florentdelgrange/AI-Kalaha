#!/bin/bash

#test command
T="java -cp src Games.Kalaha.Players.AiTester"
#count for non-deterministic AIs
N=10

$T $N random random
$T $N random pit_max
$T $N random kalaha_max
$T $N random kalaha_min
$T $N random sq_pit_min
$T $N random my_turn
$T $N pit_max random
$T 1 pit_max pit_max
$T 1 pit_max kalaha_max
$T 1 pit_max kalaha_min
$T 1 pit_max sq_pit_min
$T $N pit_max my_turn
$T $N kalaha_max random
$T 1 kalaha_max pit_max
$T 1 kalaha_max kalaha_max
$T 1 kalaha_max kalaha_min
$T 1 kalaha_max sq_pit_min
$T $N kalaha_max my_turn
$T $N kalaha_min random
$T 1 kalaha_min pit_max
$T 1 kalaha_min kalaha_max
$T 1 kalaha_min kalaha_min
$T 1 kalaha_min sq_pit_min
$T $N kalaha_min my_turn
$T $N sq_pit_min random
$T 1 sq_pit_min pit_max
$T 1 sq_pit_min kalaha_max
$T 1 sq_pit_min kalaha_min
$T 1 sq_pit_min sq_pit_min
$T $N sq_pit_min my_turn
$T $N my_turn random
$T $N my_turn pit_max
$T $N my_turn kalaha_max
$T $N my_turn kalaha_min
$T $N my_turn sq_pit_min
$T $N my_turn my_turn

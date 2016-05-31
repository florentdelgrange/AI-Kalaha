#!/bin/bash

#test command
T="java -cp src Games.Kalaha.Players.AiTester"
#count for non-deterministic AIs
N=1000
#count for deterministic AIs
D=10
#count for self tests
S=10

#prints stats
function stats {
	sort|uniq -c
}

#removes random from the output
function norandom {
	grep -v random
}


#special case
function self {
	echo SELF $1; $T $S $1 $1|stats
}

#is an AI really better than random?
function placebo {
	echo PLACEBO $1; $T $D random $1|stats
	echo $1 PLACEBO; $T $D $1 random|stats
}

#compares deterministic AIs
function compare {
	echo $1 vs. $2; $T $D $1 random $2|norandom|stats
	echo $2 vs. $1; $T $D $2 random $1|norandom|stats
}


echo RANDOM; $T $N random random|stats
placebo pit_max
placebo kalaha_max
placebo kalaha_min
placebo sq_pit_min
placebo my_turn
self pit_max
self kalaha_max
self kalaha_min
self sq_pit_min
self my_turn
compare pit_max kalaha_max
compare pit_max kalaha_min
compare pit_max sq_pit_min
compare pit_max my_turn
compare kalaha_max kalaha_min
compare kalaha_max sq_pit_min
compare kalaha_max my_turn
compare kalaha_min sq_pit_min
compare kalaha_min my_turn
compare sq_pit_min my_turn

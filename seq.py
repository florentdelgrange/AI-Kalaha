def seq(pits):
	"""
	Computes a particular winning strategy (as a list of moves).
	If it doesn't exist, returns None.
	>>> seq([0, 0, 0])
	[]
	>>> seq([0, 0, 1])
	[2]
	>>> seq([0, 2, 0])
	[1, 2]
	>>> seq([0, 2, 1])
	[2, 1, 2]
	>>> seq([3, 1, 0])
	[0, 2, 1, 2]
	>>> seq([3, 1, 1])
	[2, 0, 2, 1, 2]
	"""
	return seq_r(pits, [])

def seq_r(pits, moves):
	"""
	pits get temporarily modified but is restored upon exit.
	moves contains the start of the current winning strategy.
	"""
	all_zeros = True
	for i, p in enumerate(pits):
		all_zeros = all_zeros and p==0
		#the last token lands in the mancala
		if len(pits)-i == p:
			moves.append(i)
			#do
			pits[i] = 0
			for j in range(i+1, len(pits)):
				pits[j] += 1
			#recursive call
			ret = seq_r(pits, moves)
			#undo
			pits[i] = p
			for j in range(i+1, len(pits)):
				pits[j] -= 1
			#use the recursive result
			if ret == None:
				#forget about this move
				moves.pop()
			else:
				#success (by induction)
				return moves
	if all_zeros:
		#success (nothing to do!)
		return moves
	else:
		#we can't empty each of our pits
		return None


if __name__ == "__main__":
	import sys
	if "--test" in sys.argv[1:]:
		import doctest
		doctest.testmod()
	else:
		print seq(map(lambda x: int(x), sys.argv[1:]))

dot = False

def pretty(pits):
	"""
	Pretty-prints a pit list.
	>>> print pretty([2,0])
	20
	>>> print pretty([0])
	0
	"""
	return "".join(map(lambda x: str(x), pits))

def debug(before, move, after, dot):
	"""
	Displays a dot edge.
	>>> debug([2,0], 0, [0,0], True)
	20 -> 00 [label=1] ;
	>>> debug([0], None, [0], True)
	0 -> 0  ;
	"""
	if dot:
		label = "" if move==None else "[label="+str(move+1)+"]"
		print pretty(before), "->", pretty(after), label, ";"

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
			#debug copy
			after = pits[:]
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
				debug(pits, i, after, dot)
				return moves
	if all_zeros:
		#success (nothing to do!)
		debug(pits, None, pits, dot)
		return moves
	else:
		#we can't empty each of our pits
		return None

def all_possible(l, m):
	if l == 0:
		yield []
	else:
		assert(l>0)
		for x in range(m+1):
			for y in all_possible(l-1, m):
				yield [x]+y


if __name__ == "__main__":
	import sys
	def show_help():
		name = "seq.sh"
		print >>sys.stderr, "usage:\t%s --test [-v]" % name
		print >>sys.stderr, "\t%s explain a_0 [...]" % name
		print >>sys.stderr, "\t%s generate length max" % name
		sys.exit(1)
	if "--test" in sys.argv[1:]:
		import doctest
		doctest.testmod()
	elif len(sys.argv) == 1:
		show_help()
	else:
		dot = True
		if sys.argv[1]=="explain" and len(sys.argv)>2:
			seq(map(lambda x: int(x), sys.argv[2:]))
		elif sys.argv[1] == "generate" and len(sys.argv)==4:
			for s in all_possible(int(sys.argv[2]), int(sys.argv[3])):
				seq(s)
		else:
			show_help()

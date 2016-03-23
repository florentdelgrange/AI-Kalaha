package Games.Kalaha.Boards;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import FX.BoardMaker;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class FromFile extends Board {
	
	public static class Maker implements BoardMaker<Integer, Integer, Board, String> {
		
		ComboBox<Path> fileCombo;
		
		public Maker() {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			System.out.println("Current relative path is: " + s);
			
			try (Stream<Path> paths = Files.list(Paths.get("src", "Games", "Kalaha", "Boards"))) {
				fileCombo = new ComboBox<>();
				paths.filter(p -> p.toString().endsWith(".board")).forEach(p -> fileCombo.getItems().add(p));
			}
			catch (IOException e) {
				System.err.println("Unable to read boards directory.");
				System.exit(1);
			}
		}
		
		@Override
		public Node getConfigPane() {
			return new HBox(fileCombo);
		}

		@Override
		public Board getBoard(List<String> avatars) {
			return new FromFile(fileCombo.getValue(), avatars);
		}
		
		@Override
		public String toString() {
			return "From file";
		}
		
	}
	
	private final List<String> avatars;
	private final Path path;
	
	private final ArrayList<Integer> kalahas = new ArrayList<>();
	private final Map<Integer, String> owners = new HashMap<>();
	private final Map<Integer, List<Integer>> captures = new HashMap<>();
	
	public FromFile(Path path, List<String> avatars) {
		super(nbCells(path), avatars);
		
		this.path = path;
		this.avatars = avatars;
		
		try (Stream<String> lines = Files.lines(path)) {
			lines.filter(l -> !l.isEmpty()).forEach(l -> readline(l, avatars));
		}
		catch (IOException e) {
			System.err.println("Error reading board");
			System.exit(1);
		}
	}

	private int lineno = 0;
	
	private final void readline(String line, List<String> avatars) {
		String[] parts = line.split("[ \t]");
		if (parts.length == 3 && parts[2].equals("K")) {
			kalahas.add(lineno);
			captures.put(lineno, new ArrayList<>());
		}
		else {
			ArrayList<Integer> capts = new ArrayList<>();
			for (int i = 2; i < parts.length; ++i) {
				capts.add(Integer.parseInt(parts[i]));
			}
			captures.put(lineno, capts);
		}
		owners.put(lineno, avatars.get(Integer.valueOf(parts[0])));
		setPieceAt(lineno, Integer.valueOf(parts[1]));
		++lineno;
	}
	
	@Override
	public List<Integer> getCaptures(Integer c) {
		return captures.get(coordinate(c));
	}
	
	private static int nbCells(Path path) {
		try (Stream<String> lines = Files.lines(path)) {
			return (int) lines.count();
		}
		catch (IOException e) {
			System.err.println("Error reading board");
			System.exit(1);
			return 0;
		}
	}

	@Override
	public String getPlayer(Integer c) {
		return owners.get(coordinate(c));
	}

	@Override
	public boolean isKalaha(Integer c) {
		return kalahas.contains(coordinate(c));
	}
	
	protected FromFile(FromFile board) {
		super(board);
		
		this.path = board.path;
		this.avatars = board.avatars;
		
		for (int i = 0; i < getLength(); ++i) {
			if (board.isKalaha(i)) {
				kalahas.add(i);
			}
			owners.put(i, board.getPlayer(i));
			captures.put(i, board.getCaptures(i));
		}
	}
	
	@Override
	public FromFile readOnlyBoard() {
		return new FromFile(this);
	}
	
	@Override
	public FromFile clone() {
		FromFile clone = new FromFile(path, avatars);
		for (int i = 0; i < getLength(); ++i) {
			clone.setPieceAt(i, getPieceAt(i));
		}
		return clone;
	}

}

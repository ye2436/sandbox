package interview.li.other;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TextIterator implements Iterable<String>{
	
	private BufferedReader br;
	private ArrayList<String> buffer;
	
	public TextIterator(String fileName) throws FileNotFoundException {
		this.br = new BufferedReader(new FileReader(fileName));
		this.buffer = new ArrayList<>();
	}
	
	public void push() throws IOException {
		int index = 0;
		String line;
		buffer = new ArrayList<>();
		while ((line = br.readLine()) != null && index < 2) {
			buffer.add(line);
			index++;
		}
	}

	@Override
	public Iterator<String> iterator() {
		if (!buffer.iterator().hasNext()) {
			try {
				push();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer.iterator();
	}

	public static void main(String[] args) throws FileNotFoundException {
		TextIterator tt = new TextIterator("/Users/xzhu/Desktop/test.text");
		Iterator<String> it = tt.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}


}

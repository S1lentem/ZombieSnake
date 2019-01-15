package filesys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

public class SerializationGame {
	private SavedObject obj;
	private final String pathToSave;
	
	public SerializationGame(String pathToSaveDir, SavedObject obj) {
		this.obj = obj;
		this.pathToSave = Paths.get(pathToSaveDir, "save.out").toString();
	}
	
	public SerializationGame(String pathToSaveDir) {
		this.pathToSave = Paths.get(pathToSaveDir, "save.out").toString();
	}
	
	public void serialization() throws IOException {
		tryCreatSaveFile();
		FileOutputStream file = new FileOutputStream(pathToSave);
		ObjectOutputStream obj = new ObjectOutputStream(file);
		try
		{
			obj.writeObject(this.obj);
			obj.flush();
		}
		finally {
			obj.close();
		}
	}
	
	public void deserialization() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream(pathToSave);
		ObjectInputStream obj = new ObjectInputStream(file);
		try
		{
			this.obj = (SavedObject)obj.readObject();
		}
		finally {
			obj.close();
		}
		System.out.println(this.obj.getTrueFood().getX());
	}
	
	public SavedObject getSaveObject() {
		return obj;
	}
	
	private void tryCreatSaveFile() throws IOException {
		File file = new File(pathToSave);
		if (!file.exists()) {
			file.createNewFile();
		}
	}
}

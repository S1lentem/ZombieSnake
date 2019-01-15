package field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import gameobjects.Border;
import gameobjects.Modifiable;
import input.KeyInput;

public final class Director implements Serializable {
	private ArrayList<Modifiable> objects = new ArrayList<Modifiable>();
	private Modifiable object;
	private boolean isInstalledRandomModification;
	
	public Director(Modifiable ... objects) {
		for (Modifiable object: objects) {
			this.objects.add(object);
		}
		setRandomObjectModification();
	}
	
	public void setRandomObjectModification() {
		Random rn = new Random();
		int index = rn.nextInt(this.objects.size());
		object = objects.get(index);
	}
	
	public void runObjectModification() {
		object.setModification();
		isInstalledRandomModification = true;
	}
	
	public void resetAllModification() {
		for (Modifiable object: this.objects) {
			object.resetModification();
		}
		isInstalledRandomModification = false;
	}
	
	public void addModifiableObject(Modifiable object) {
		this.objects.add(object);
	}
	
	public int size() {
		return this.objects.size();
	}
	
	public boolean isInstalledModification() {
		return this.isInstalledRandomModification;
	}
	
	public String getInfoModification() {
		return "Next modification: " + this.object.getDescriptionModification();
	}
}

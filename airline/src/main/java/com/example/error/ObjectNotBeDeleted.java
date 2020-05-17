package com.example.error;

public class ObjectNotBeDeleted extends RuntimeException {
	
	public ObjectNotBeDeleted(Long id,Class class1, Class class2) {
		super(id+" id li " + class1.getSimpleName()+ " Objesi silinemez, bagli "
				+ class2.getSimpleName()+ " Objeleri bulunuyor");
	}
}

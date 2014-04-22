/**
 * Convert an object to a base64 encoded String
 * @param object - the object to convert base64 encoded String
 * @return - the object encoded as base64 encoded String
 */
public String objectToBase64String(Object object){
	ObjectOutputStream oos = null;
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	try {
		oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
	} catch (IOException e) {
		new RuntimeException(e);
	}
	byte [] objectBytes = baos.toByteArray();
	return Base64.encodeToString(objectBytes, Base64.NO_WRAP);
}

/**
 * Given a base64 encoded String convert (try to) convert the base64 encoded String to an object
 * @param base64String - base64 encoded String to convert to an object
 * @return - an Object that as the result of convert the base64 encoded String to Object
 */
public Object base64StringToObject(String base64String){
	byte [] objectBytes = Base64.decode(base64String, Base64.NO_WRAP);
	ObjectInputStream ois = null;
	Object object; 
	try {
		ois = new ObjectInputStream(new ByteArrayInputStream(objectBytes));
	        object = ois.readObject();
	        return object;  
	} catch (StreamCorruptedException e) {
		throw new RuntimeException(e);
	} catch (IOException e) {
		throw new RuntimeException(e);
	} catch (ClassNotFoundException e) {
		throw new RuntimeException(e);
	} 
}

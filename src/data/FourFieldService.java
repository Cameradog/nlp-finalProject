package data;


public class FourFieldService {
	// using singleton pattern to make sure you
	// establish this instance only once.
	private static FourFieldService fieldService = null;

	// get service and you can use function and you can access this java's
	// function
	// for example:FieldService.getServ().createNewField
	public static FourFieldService getServ() {
		if (fieldService == null) {
			fieldService = new FourFieldService();
		}
		return fieldService;
	}
	
	public void createField(FourField f){
		
	}
}

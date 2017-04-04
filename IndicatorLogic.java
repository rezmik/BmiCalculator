
public class IndicatorLogic {
	private double height, weight, bmi;
	public IndicatorLogic(double height, double weight) {
		this.height = height;
		this.weight = weight;
	}
	public double bmiCalculation() {
		if (height > 3) {
			height /= 100;
		}
		
		bmi = weight / Math.pow(height,2);
		bmi = Math.round(bmi * 100);
		bmi /= 100;
		return bmi;
	}
	
	public String setMessageText(double bmi) {
		String message;
		if (bmi < 16) {
			message = "BMI wskazuje, ze jestes WYGLODZONY/A, idz do lekarza!";
		} else if (bmi >= 16 && bmi < 17) {
			message = "BMI wskazuje, ze jestes WYCHUDZONY/A, rozwaz wizyte u lekarza!";
		} else if (bmi >= 17 && bmi < 18.5) {
			message = "BMI wskazuje, ze masz NIEDOWAGE, musisz wiecej jesc!";
		} else if (bmi >= 18.5 && bmi < 25) {
			message = "Wskaznik BMI w normie!";
		} else if (bmi >= 25 && bmi < 30) {
			message = "BMI wskazuje, ze masz NADWAGE, rozwaz diete!";
		} else if (bmi >= 30 && bmi < 35) {
			message = "BMI wskazuje, ze masz I STOPIEN OTYLOSCI, skonsultuj sie z lekarzem, na pewno Ci pomoze!";
		} else if (bmi >= 35 && bmi < 40) {
			message = "BMI wskazuje, ze masz II STOPIEN OTYLOSCI, musisz isc do lekarza";
		} else {
			message = "BMI wskazuje, ze masz SKRAJNA OTYLOSC,\n idz do lekarza bo to moze \nsie zle skonczyc!";
		}
		return message;
	}
}

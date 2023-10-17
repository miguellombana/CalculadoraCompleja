package dad.CalculadoraCompleja;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Complejo {

    private DoubleProperty real = new SimpleDoubleProperty();
    private DoubleProperty imaginario = new SimpleDoubleProperty();
    
    
    public DoubleProperty getRealProperty() {
        return real;
    }

    public DoubleProperty getImaginarioProperty() {
        return imaginario;
    }
    
	public Double getReal() {
		return real.get();
	}
	
	public void setReal(Double real) {
		this.real.set(real);
	}


	public Double getImaginario() {
		return imaginario.get();
	}

	public void setImaginario(Double imaginario) {
		this.imaginario.set(imaginario);
	}
       
}

package vos;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

public class ListaAdministradores {
	
		
		@JsonProperty(value="administradores")
		private List<Administrador> administradores;
		
		
		public ListaAdministradores( @JsonProperty(value="administradores")List<Administrador> administradores){
			this.administradores = administradores;
		}

		
		public List<Administrador> getAdministradores() {
			return administradores;
		}

		public void setAdministradores(List<Administrador> administradores) {
			this.administradores = administradores;
		}
		
}

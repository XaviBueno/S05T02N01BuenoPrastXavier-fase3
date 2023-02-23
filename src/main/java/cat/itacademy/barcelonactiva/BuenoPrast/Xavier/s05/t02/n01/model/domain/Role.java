package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	public Role() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String name;

	@Column
	private String description;

}

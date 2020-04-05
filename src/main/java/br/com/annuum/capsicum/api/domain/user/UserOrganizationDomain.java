package br.com.annuum.capsicum.api.domain.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "user_organization")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class UserOrganizationDomain extends UserDomain {

    @CNPJ
    private String cnpj;

}

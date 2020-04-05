package br.com.annuum.capsicum.api.domain.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
//@Entity
//@Table(name = "user_contributor")
//@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class UserGroupDomain extends UserDomain {

    //TODO - Luis Henrique Hendges | Acho que devemos mudar a  forma de controle dos grupos. Ele não pode ser considerado um usuário

}

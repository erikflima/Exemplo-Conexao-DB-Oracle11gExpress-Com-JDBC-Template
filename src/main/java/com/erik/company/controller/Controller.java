package com.erik.company.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.erik.company.model.ResultadoModel;
import com.erik.company.model.UsuarioModel;
import com.erik.company.repository.UsuarioRepository;



@RestController                                            //Anotacao do Spring que torna essa classe um endpoint.
@RequestMapping(value="/api", produces="application/json") //Anotacao do Spring que uso para definir qual sera o caminho do endpoint. Digo que recebe json e produso json.
@CrossOrigin(origins = "*")                                //Anotacao do Spring que uso para dizer que esse controller pode receber requisicoes de qualquer origem, ou seja, requisicoes de qualquer dominio(url), mas eu poderia restrigir, e colocar quais domininios podem fazer requisicoes para esse controller.
public class Controller {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	/**
	 * ESSE MÉTODO CADASTRA UM NOVO USUÁRIO COMO MOSTRA O EXEMPLO ABAIXO.
	 * 
	 * EXEMPLO:
	 * 
	 * 	URL: http://localhost:8090/api/usuario
	 * 
	 *  REQUEST JSON:
	 *  			{
	 *  				"login":"Erik",
	 *  				"senha":"123456",
	 *  				"ativo":0
	 *  			}
	 *  
	 *  
	 *  RESPONSE JSON:
	 *  			{
	 *  				"codigo": 1,
	 *  				"mensagem": "Registro cadastrado com sucesso!"
	 *  			}
	 * */
	@PostMapping(value="/usuario", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResultadoModel cadastrar( @RequestBody UsuarioModel usuario ){
		
		ResultadoModel resultadoModel = usuarioRepository.cadastrar(usuario);
		
		return resultadoModel;
	}
	
	
	//--------
	
	
	/**
	 * ATUALIZA UM USUÁRIO COMO MOSTRA O EXEMPLO ABAIXO.
	 * 
	 * EXEMPLO: 
	 * 	 
	 * 	 URL: http://localhost:8034/api/usuario
	 * 	 
	 *   REQUEST JSON:   
	 *				{
	 *					"codigo": 1,
	 *					"login": "cicero",
	 *					"senha": "1234",
	 *					"ativo": 1
	 *				}
	 *      
	 *      
	 *   RESPONSE JSON:
	 *				{
	 *					"codigo": 1,
	 *					"mensagem": "Registro atualizado com sucesso!"
	 *				}
	 *         
	 *         
	 *         
	 *         
	 * 
	 * @param usuario
	 * @return
	 */
	@PutMapping(value="/usuario", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResultadoModel atualizar( @RequestBody UsuarioModel usuario ){
		
		ResultadoModel resultadoModel = usuarioRepository.atualizarUsuario(usuario);
		
		return resultadoModel; 
	}
	

	//--------

	
	/**
	 * CONSULTA TODOS OS USUÁRIOS CADASTRADOS COMO MOSTRA O EXEMPLO ABAIXO
	 * 
	 * EXEMPLO:
	 * 	 URL:http://localhost:8034/api/usuario
	 * 
	 *   RESPONSE JSON:
	 *				[
	 *					{
	 *					  "codigo": 16,
	 *					  "login": "Erik1",
	 *					  "senha": "1234561",
	 *					  "ativo": 0
	 *					  },
	 *					  {
	 *					  "codigo": 14,
	 *					  "login": "Erik2",
	 *					  "senha": "123456",
	 *					  "ativo": 1
	 *					  }
	 *				]   	
	 *   
	 *   
	 * 
	 * @return
	 */
	@GetMapping(value="/usuario", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UsuarioModel> consultar(){
		
		List<UsuarioModel> lista = usuarioRepository.usuarios();
		
		return lista;
	
	}
	
	
	//--------
	
	
	/**
	 * SELECIONA UM USUÁRIO PELO CÓDIGO COMO MOSTRA O EXEMPLO ABAIXO
	 * 
	 * EXEMPLO:
	 *   
	 *   URL REQUEST:http://localhost:8034/api/usuario/15
	 *   
	 *   
	 *   RESPONSE JSON:
	 *   		{
	 *   			"codigo": 1,
	 *   			"login": "Erik",
	 *   			"senha": "1234561",
	 *   			"ativo": 0
	 *   		}
	 *   		
	 * 
	 * @param codigo
	 * @return
	 */
	@GetMapping(value="/usuario/{codigo}", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UsuarioModel selecionar( @PathVariable("codigo") Integer codigo){
	
		UsuarioModel usuario = usuarioRepository.usuario(codigo);
		
		return usuario;
	}
	
	
	//--------
	
	
	/**
	 * EXCLUI UM USUÁRIO PELO CÓDIGO COMO MOSTRA O EXEMPLO ABAIXO;
	 * 
	 * EXEMPLO:
	 * 	
	 * 	URL REQUEST:http://localhost:8034/api/usuario/16
	 * 
	 * 	RESPONSE JSON:
	 * 				{
	 * 					"codigo": 1,
	 * 					"mensagem": "Registro excluido com sucesso!"
	 * 				}
	 * 
	 * @param codigo
	 * @return
	 */
	@DeleteMapping(value="/usuario/{codigo}", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResultadoModel delete( @PathVariable("codigo") Integer codigo){
	
		ResultadoModel resultadoModel = usuarioRepository.excluirUsuario(codigo);
		
		return resultadoModel;
	}
	
	
}
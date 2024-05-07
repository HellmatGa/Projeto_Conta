package controller;

import java.util.ArrayList;

import model.Conta;
import repository.ContaRepository;

public class ContaController implements ContaRepository {
	
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numeo = 0;

	@Override
	public void procurarPorNumero(int numero) {
		var conta = bucarNaCollection(numero);
		
		if (conta != null) {
			conta.visualizar();
		} else
			System.out.println("\nA Conta número: " + numero + " não foi encontrada!");	
	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}	
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\nA Conta número: " + conta.getNumero() + " foi criada com sucesso!");
	}

	@Override
	public void atualizar(Conta conta) {
		var buscarConta = bucarNaCollection(conta.getNumero());
		
		if (buscarConta != null) {
			listaContas.set(listaContas.indexOf(buscarConta), conta);
			System.out.println("\nA conta número: " + conta.getNumero() + " foi atualizada com sucesso!");
		} else
			System.out.println("\nA Conta número: " + conta.getNumero() + " não foi encontrada!");
		}

	@Override
	public void deletar(int numero) {
		var conta = bucarNaCollection(numero);
		
		if (conta != null) {
			if (listaContas.remove(conta) == true);
				System.out.println("\nA conta número: " + numero + " foi deletada com sucesso!");
		} else
			System.out.println("\nA conta número: " + numero + "não foi encontrada");	
	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = bucarNaCollection(numero);
		
		if (conta != null) {
			
			if(conta.sacar(valor) == true);
				System.out.println("\nO Saque na Conta numero: " + numero + " foi efetuado com sucesso!");
				
		}else 
			System.out.println("\nA Conta número: " + numero + "não foi encontrada");
	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = bucarNaCollection(numero);
		
		if (conta != null) {
			conta.depositar(valor);
			System.out.println("\nO Depósito na Conta número: " + numero + "foi efetuado com sucesso!");
		}else 
			System.out.println("\nA Conta número: " + numero + "não foi encontrada ou a Conta destino não é uma Conta Corrente!");
		}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = bucarNaCollection(numeroOrigem);
		var contaDestino = bucarNaCollection(numeroDestino);
		
		if (contaOrigem != null && contaDestino != null) {
			
			if (contaOrigem.sacar(valor) == true) {
				contaDestino.depositar(valor);
				System.out.println("\nA Transferência foi realizada com sucesso!");
			}
		} else 
			System.out.println("\nA Conta de Origem e/ou Destino não foram encontradas!");
	}

	public int gerarNumero() {
		int numero = 0;
		return ++ numero;
	}
	
	public Conta bucarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}
		
		return null;
	}
}
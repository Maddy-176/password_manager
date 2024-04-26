package com.example.password_manager.demo.password_manager;

import com.example.password_manager.demo.password_manager.model.PasswordRecord;
import com.example.password_manager.demo.password_manager.model.Vault;
import com.example.password_manager.demo.password_manager.service.PasswordRecordService;
import com.example.password_manager.demo.password_manager.service.VaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private final Map<String,Vault> authenticatedVaults;
	VaultService vaultService;
	PasswordRecordService passwordRecordService;

	private Boolean isAuthenticated= false;
    @Autowired
	public Application(VaultService vaultService,PasswordRecordService passwordRecordService){
		this.vaultService=vaultService;
		this.authenticatedVaults=new HashMap();
		this.passwordRecordService= passwordRecordService;
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String ...args){
		Scanner scanner= new Scanner(System.in);
		System.out.println("Welcome To Password Manager CLI");
		while(true){
			System.out.println("Choose and option:");
			System.out.println("1. Create a new Vault");
			System.out.println("2. Authenticate a Vault");
			System.out.println("3. Add password to the vault");
			System.out.println("4. Fetch password for the vault");
			System.out.println("5. Quit");
			int choice= scanner.nextInt();
			scanner.nextLine();
			switch (choice){
				case 1:
					vaultService.createVault(scanner);
					break;
				case 2:
					authenticateVault(scanner);
					break;
				case 3:
					 passwordRecordService.storePassword(scanner,checkAuthtentication(),getCurrentVault());
					break;
				case 4:
					passwordRecordService.retrieveRecords(scanner,checkAuthtentication(),getCurrentVault());
					break;
				case 5:
					System.out.println("Quit");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");

			}




		}
	}

	private void authenticateVault(Scanner scanner){
		System.out.println("Enter Vault Name");
		String vaultName= scanner.nextLine();
		System.out.println("Enter Vault Password");
		String password= scanner.nextLine();
		Vault vault= vaultService.AuthenticateVault(scanner);
		if (vault != null) {
			System.out.println("Vault authenticated successfully");
			authenticatedVaults.put(vault.getId().toString(), vault);
		} else {
			System.out.println("Failed to authenticate the vault");
		}
	}
	private boolean checkAuthtentication(){
		if(authenticatedVaults.isEmpty()){
			System.out.println("Pleae autheticate the vault");
			return false;
		}
		return true;
	}

	private Vault getCurrentVault(){
		if(authenticatedVaults.size()==1){
			return authenticatedVaults.values().iterator().next();
		}else{
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Vault name");
			String vaultName= scanner.nextLine();
			return authenticatedVaults.get(vaultName);
		}
	}

}

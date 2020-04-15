package IoDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import motivos.Salidas;
import Individuos.Persona;


public class IOdatos {
	public static final String dir="./datos";
	public static final String dirpol="./datos/personas.dat";
	public static final String dirsal="./datos/salida.dat";

	
	private static void mkdirectorio() {
		File f=new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
	}
	
	public static void guardarPers(ArrayList<Persona> vPersonas) {
		mkdirectorio();
		File f=new File(dirpol);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				fos = new FileOutputStream(f);
				oos = new ObjectOutputStream(fos);

				oos.writeObject(vPersonas);

			} catch (FileNotFoundException e) {
				// e.printStackTrace();
			} catch (IOException e) {
				// e.printStackTrace();
			} finally {
				try {
					oos.close();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
	}
	public static void guardarSal(ArrayList<Salidas> vSalida) {
		mkdirectorio();
		
		File f=new File(dirsal);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				fos = new FileOutputStream(f);
				oos = new ObjectOutputStream(fos);

				oos.writeObject(vSalida);

			} catch (FileNotFoundException e) {
				// e.printStackTrace();
			} catch (IOException e) {
				// e.printStackTrace();
			} finally {
				try {
					oos.close();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		
	}
	

	
	public static ArrayList<Persona> cargarpersona() {
		mkdirectorio();
		ArrayList<Persona> vPersona = new ArrayList<Persona>();
		
		File file =new File(dirpol);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return vPersona;
		}
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
//				ois.readObject();
			vPersona = (ArrayList<Persona>) ois.readObject();

		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
		} finally {
			try {
				if (ois!=null)
					ois.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}

		return vPersona;
	}
	
	public static ArrayList<Salidas> cargarSalida() {
		mkdirectorio();
		ArrayList<Salidas> vPersona = new ArrayList<Salidas>();
		
		File file =new File(dirsal);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return vPersona;
		}
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
//				ois.readObject();
			vPersona = (ArrayList<Salidas>) ois.readObject();

		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
		} finally {
			try {
				if (ois!=null)
					ois.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}

		return vPersona;
	}
	}

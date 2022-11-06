package logica;

import hospital.CitaMedica;
import hospital.personas.EmpleadoEventual;
import hospital.personas.EmpleadoPlanilla;
import hospital.personas.Medico;
import hospital.personas.Paciente;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Procesos {
	String dni, nombre, apellido, fechaNacimiento, direccion, ciudad, codEmpleado, numHorasExt, fechaIngreso, area,
			cargo;

	ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();

	ArrayList<EmpleadoPlanilla> listaEmpleadoPlanilla = new ArrayList<EmpleadoPlanilla>();

	ArrayList<EmpleadoEventual> listaEmpleadoEventual = new ArrayList<EmpleadoEventual>();
	ArrayList<Medico> listaMedicos = new ArrayList<Medico>();

	CitaMedica arregloCitas[];
	int cantCitas = 0;

	public Procesos() {
		int numCitas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de citas medicas por dia"));

		arregloCitas = new CitaMedica[numCitas];
		presentarMenu();
	}

	private void presentarMenu() {
		int opc;
		String cad = "SISTEMA HOSPITALARIO \nRegistro\n\n";
		cad += "1. Registrar Usuarios\n";
		cad += "2. Registrar Cita Medica\n";
		cad += "3. Imprimir Datos\n";
		cad += "4. Salir\n\n";
		do {
			opc = Integer.parseInt(JOptionPane.showInputDialog(cad + "Ingrese una opcion"));

			switch (opc) {
			case 1:
				cargarMenuRegistro();
				break;
			case 2:
				System.out.println(cantCitas + "<" + arregloCitas.length);
				if (cantCitas < arregloCitas.length) {
					if (validaRegistrosPrevios()) {
						registrarCitaMedica();
					} else {
						JOptionPane.showMessageDialog(null,
								"No se puede registrar la cita, verifique que los empleados por planilla,\n"
										+ "medicos y pacientes se encuentren previamente\nregistrados en elsistema",
								"Advertencia", JOptionPane.WARNING_MESSAGE);

					}
				} else {
					JOptionPane.showMessageDialog(null,
							"No se puede registrar la cita, Ha superado el numero de citas por dia", "Advertencia",
							JOptionPane.WARNING_MESSAGE);

				}
				break;
			case 3:
				if (validaRegistrosEmpleados()) {
					imprimirDatos();
				}
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Chao");
				break;
			default:
				break;
			}
		} while (opc != 4);
		JOptionPane.showMessageDialog(null, "salio");
	}

	private void imprimirDatos() {
		String menuMsj = "Menu Reportes\n\n";
		menuMsj += "1. Imprimir Empleados de Planilla\n";
		menuMsj += "2. Imprimir Empleados Eventuales\n";
		menuMsj += "3. Imprimir Medicos\n";
		menuMsj += "4. Imprimir Pacientes\n";
		menuMsj += "5. Imprimir Citas\n";
		menuMsj += "6. Salir\n\n";
		int cod = Integer.parseInt(JOptionPane.showInputDialog(menuMsj + "Seleccione una opcion"));

		switch (cod) {
		case 1:
			if (!listaEmpleadoPlanilla.isEmpty()) {
				System.out
						.println("***************************Empleados Planilla**************************************");
				for (int i = 0; i < listaEmpleadoPlanilla.size(); i++) {

					System.out.println("Numero DNI: " + listaEmpleadoPlanilla.get(i).getDni());
					System.out.println("Nombre:" + listaEmpleadoPlanilla.get(i).getNombre() + ""
							+ listaEmpleadoPlanilla.get(i).getApellido());

					System.out.println("Codigo Empleado:" + listaEmpleadoPlanilla.get(i).getCodEmpleado());

					System.out.println("Cargo:" + listaEmpleadoPlanilla.get(i).getCargo());

					System.out.println("Salario mensual:" + listaEmpleadoPlanilla.get(i).getSalarioMensual());
					System.out.println();
				}

				System.out.println(
						"*************************************************************************************");

			} else {
				JOptionPane.showMessageDialog(null, "No existe informacion registrada", "Error",
						JOptionPane.ERROR_MESSAGE);

			}
			break;
		case 2:
			if (!listaEmpleadoEventual.isEmpty()) {

				System.out.println(
						"******************************EmpleadosEventuales**************************************");
				for (int i = 0; i < listaEmpleadoEventual.size(); i++) {

					System.out.println("Numero DNI:" + listaEmpleadoEventual.get(i).getDni());

					System.out.println("Nombre:" + listaEmpleadoEventual.get(i).getNombre() + ""
							+ listaEmpleadoEventual.get(i).getApellido());

					System.out.println("Codigo Empleado:" + listaEmpleadoEventual.get(i).getCodEmpleado());

					System.out.println("Cargo:" + listaEmpleadoEventual.get(i).getCargo());

					System.out.println("Honorarios por hora:" + listaEmpleadoEventual.get(i).getHonorariosHora());

					System.out.println(
							"Fecha termino de contrato: " + listaEmpleadoEventual.get(i).getFechaTerminoContrato());

					System.out.println();
				}

				System.out.println(
						"*************************************************************************************");

			} else {
				JOptionPane.showMessageDialog(null, "No existe informacion registrada", "Error",
						JOptionPane.ERROR_MESSAGE);

			}
			break;
		case 3:
			if (!listaMedicos.isEmpty()) {

				System.out.println(
						"**********************************Medicos*********************************************");

				for (int i = 0; i < listaMedicos.size(); i++) {
					System.out.println("Numero DNI:" + listaMedicos.get(i).getDni());

					System.out.println(
							"Nombre:" + listaMedicos.get(i).getNombre() + " " + listaMedicos.get(i).getApellido());
					System.out.println("Codigo Empleado:" + listaMedicos.get(i).getCodEmpleado());

					System.out.println("Cargo:" + listaMedicos.get(i).getCargo());

					System.out.println("Especialidad:" + listaMedicos.get(i).getEspecialidad());

					System.out.println("Numero de consultorio:" + listaMedicos.get(i).getNumConsultorio());

					System.out.println();
				}

				System.out.println(
						"*************************************************************************************");

			} else {
				JOptionPane.showMessageDialog(null, "No existe informacion registrada", "Error",
						JOptionPane.ERROR_MESSAGE);

			}
			break;
		case 4:
			if (!listaPacientes.isEmpty()) {

				System.out.println(
						"**********************************Pacientes*********************************************");

				for (int i = 0; i < listaPacientes.size(); i++) {
					System.out.println("Numero DNI:" + listaPacientes.get(i).getDni());

					System.out.println(
							"Nombre:" + listaPacientes.get(i).getNombre() + " " + listaPacientes.get(i).getApellido());
					System.out.println("Numero Historia Clinica:" + listaPacientes.get(i).getNumHistoria());

					System.out.println("Sexo:" + listaPacientes.get(i).getSexo());

					System.out.println("Grupo Sanguineo:" + listaPacientes.get(i).getGrupoSanguineo());

					System.out.println("Lista Medicamentos");
					for (int j = 0; j < listaPacientes.get(i).getListaMedicamentos().length; j++) {
						System.out.print(listaPacientes.get(i).getListaMedicamentos()[j] + " / ");

					}
					System.out.println();
				}

				System.out.println(
						"*************************************************************************************");

			} else {
				JOptionPane.showMessageDialog(null, "No existe informacion registrada", "Error",
						JOptionPane.ERROR_MESSAGE);

			}
			break;
		case 5:
			if (arregloCitas != null) {

				System.out.println(
						"**********************************Citas*********************************************");

				for (int i = 0; i < arregloCitas.length; i++) {
					System.out.println("Servicio:" + arregloCitas[i].getServicio());
					System.out.println("Paciente:" + arregloCitas[i].getPaciente().getNombre() + ""
							+ arregloCitas[i].getPaciente().getApellido());

					System.out.println("Medico:" + arregloCitas[i].getMedico().getNombre() + ""
							+ arregloCitas[i].getMedico().getApellido());

					System.out.println("Fecha:" + arregloCitas[i].getFecha());

					System.out.println("Hora:" + arregloCitas[i].getHora());

					System.out.println();
				}

				System.out.println(
						"*************************************************************************************");

			} else {
				JOptionPane.showMessageDialog(null, "No existe informacion registrada", "Error",
						JOptionPane.ERROR_MESSAGE);

			}
			break;
		default:
			break;
		}
	}

	private boolean validaRegistrosEmpleados() {
		boolean retorno = false;
		if (listaEmpleadoPlanilla != null ||

				listaEmpleadoEventual != null) {

			if (validarEmpleado()) {
				retorno = true;
			} else {
				JOptionPane.showMessageDialog(null, "No existen empleados con ese documento", "Error",
						JOptionPane.ERROR_MESSAGE);

			}
		} else {
			JOptionPane.showMessageDialog(null, "No existen empleados Registrados", "Error", JOptionPane.ERROR_MESSAGE);

		}
		return retorno;
	}

	private boolean validarEmpleadoPlanilla() {
		String documentoEmpleado = JOptionPane.showInputDialog("Ingrese el documento del empleado por planilla");
		boolean retorna = false;
		for (int i = 0; i < listaEmpleadoPlanilla.size(); i++) {
			if

			(documentoEmpleado.equals(listaEmpleadoPlanilla.get(i).getDni())) {

				retorna = true;
			}
		}
		return retorna;
	}

	private boolean validarEmpleado() {

		String documentoEmpleado = JOptionPane.showInputDialog("Ingrese el documento del empleado");

		boolean retorna = false;
		if (listaEmpleadoPlanilla != null) {
			for (int i = 0; i < listaEmpleadoPlanilla.size(); i++)

			{

				if

				(documentoEmpleado.equals(listaEmpleadoPlanilla.get(i).getDni())) {

					retorna = true;
				}
			}
		}
		if (retorna == false) {
			if (listaEmpleadoEventual != null) {
				for (int i = 0; i < listaEmpleadoEventual.size(); i++) {
					if

					(documentoEmpleado.equals(listaEmpleadoEventual.get(i).getDni())) {

						retorna = true;
					}
				}
			}
		}
		return retorna;
	}

	private boolean validaRegistrosPrevios() {
		boolean retorno = false;
		if (listaPacientes != null && listaMedicos != null &&

				listaEmpleadoPlanilla != null) {
			retorno = true;
		}
		return retorno;
	}

	private void registrarCitaMedica() {
		if (validarEmpleadoPlanilla()) {
			CitaMedica miCita = new CitaMedica();
			miCita.setServicio(JOptionPane.showInputDialog("Ingrese el Servicio"));

			miCita.setPaciente(asignarPaciente());
			miCita.setMedico(asignaMedico());

			miCita.setFecha(JOptionPane.showInputDialog("Ingrese la fecha"));
			miCita.setHora(JOptionPane.showInputDialog("Ingrese la Hora"));

			JOptionPane.showMessageDialog(null, "La cita se ha registrado exitosamente!!!");

			arregloCitas[cantCitas] = miCita;
			cantCitas++;

		} else {
			JOptionPane.showMessageDialog(null, "El documentono corresponde a un empleado por planilla", "Advertencia",
					JOptionPane.WARNING_MESSAGE);

		}

	}

	private Medico asignaMedico() {
		boolean repiteCiclo = false;
		Medico miMedico = null;
		do {
			String

			documentoPaciente = JOptionPane.showInputDialog("Ingrese el documento del Medico");

			for (int i = 0; i < listaMedicos.size(); i++) {
				if

				(documentoPaciente.equals(listaMedicos.get(i).getDni())) {
					miMedico = listaMedicos.get(i);
				}
			}
			if (miMedico != null) {
				repiteCiclo = false;
			} else {
				repiteCiclo = true;
				JOptionPane.showMessageDialog(null,
						"El documento no corresponde a un Medico\n" + "Por favor ingrese un documento valido",
						"Advertencia", JOptionPane.WARNING_MESSAGE);

			}
		} while (repiteCiclo == true);
		return miMedico;
	}

	private Paciente asignarPaciente() {
		boolean repiteCiclo = false;
		Paciente miPaciente = null;
		do {
			String

			documentoPaciente = JOptionPane.showInputDialog("Ingrese el documento del paciente");

			for (int i = 0; i < listaPacientes.size(); i++) {
				if

				(documentoPaciente.equals(listaPacientes.get(i).getDni())) {
					miPaciente = listaPacientes.get(i);
				}
			}
			if (miPaciente != null) {
				repiteCiclo = false;
			} else {
				repiteCiclo = true;
				JOptionPane.showMessageDialog(null,
						"El documento no corresponde a un Paciente\n" + "Por favor ingrese un documento valido",
						"Advertencia", JOptionPane.WARNING_MESSAGE);

			}

		} while (repiteCiclo == true);
		return miPaciente;
	}

	private void cargarMenuRegistro() {
		String cad = "REGISTRO DE USUARIOS\nregistro\n\n";
		cad += "1. Registrar Empleado\n";
		cad += "2. Registrar Paciente\n";
		cad += "3. Regresar\n\n";
		int

		opc = Integer.parseInt(JOptionPane.showInputDialog(cad + "Ingrese una opcion"));

		switch (opc) {
		case 1:
			String tipoEmpleado = "Tipo Empleado\n\n";
			tipoEmpleado += "1.Empleado Planilla\n";
			tipoEmpleado += "2.Empleado Eventual\n";
			tipoEmpleado += "3.Medico\n\n";
			int

			tipo = Integer.parseInt(JOptionPane.showInputDialog(tipoEmpleado + "Seleccione el tipo de empleado"));

			registrarEmpleado(tipo);
			break;
		case 2:
			registrarPaciente();
			break;
		case 3:
			break;
		default:
			break;
		}
	}

	private void registrarPaciente() {
		if (listaPacientes == null) {
			int cantPaciente = Integer
					.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de Pacientes a registrar"));

			// listaPacientes = new Paciente[cantPaciente];
			for (int i = 0; i < cantPaciente; i++) {
				JOptionPane.showMessageDialog(null, "Registro de datos paciente " + (i + 1));

				Paciente miPaciente = new Paciente();

				miPaciente.setDni(JOptionPane.showInputDialog("Ingrese el DNI del Paciente " + (i + 1)));
				miPaciente.setNombre(JOptionPane.showInputDialog("Ingrese el Nombre del Paciente " + (i + 1)));
				miPaciente.setApellido(JOptionPane.showInputDialog("Ingrese el Apellido del Paciente " + (i + 1)));

				miPaciente.setFechaNacimiento(
						JOptionPane.showInputDialog("Ingrese la fecha de Nacimiento del Paciente " + (i + 1)));
				miPaciente.setDireccion(JOptionPane.showInputDialog("Ingrese la Direccion del Paciente " + (i + 1)));
				miPaciente.setCiudad(JOptionPane.showInputDialog("Ingrese Ciudad del Paciente " + (i + 1)));

				miPaciente.setNumHistoria(JOptionPane
						.showInputDialog("Ingrese el Numero de la Historia Clinica del Paciente " + (i + 1)));
				miPaciente.setSexo(JOptionPane.showInputDialog("Ingrese el Sexo del Paciente " + (i + 1)));
				miPaciente.setGrupoSanguineo(
						JOptionPane.showInputDialog("Ingrese el Grupo Sanguineo del Paciente " + (i + 1)));

				int cantMedic = Integer.parseInt(JOptionPane.showInputDialog(
						"Cuantos medicamentos a los que es alergico el paciente " + (i + 1) + " desea Registrar?"));

				String arregloMedicamentos[] = new String[cantMedic];
				for (int j = 0; j < arregloMedicamentos.length;

						j++) {
					arregloMedicamentos[j] = JOptionPane.showInputDialog("Ingrese el medicamento " + (j + 1));
				}

				miPaciente.setListaMedicamentos(arregloMedicamentos);

				listaPacientes.add(miPaciente);
			}
			JOptionPane.showMessageDialog(null, "El registro de Pacientes se ha realizado con exito");

		} else {
			JOptionPane.showMessageDialog(null, "Ya se han registrado los Pacientes", "ERROR",
					JOptionPane.ERROR_MESSAGE);

		}
	}

	private void registrarEmpleado(int tipo) {
		switch (tipo) {
		case 1:
			if (listaEmpleadoPlanilla == null) {
				int

				cantEmpleadoPlanilla = Integer.parseInt(
						JOptionPane.showInputDialog("Ingrese lacantidad de empleados por planilla a registrar"));
				// listaEmpleadoPlanilla = new EmpleadoPlanilla[cantEmpleadoPlanilla];

				for (int i = 0; i < cantEmpleadoPlanilla; i++) {
					JOptionPane.showMessageDialog(null, "Registro de datos Empleado Planilla " + (i + 1));

					asignarValoresGeneralesEmpleado("Empleado por Planilla " + (i + 1));

					EmpleadoPlanilla miEmpleadoPlanilla = new

					EmpleadoPlanilla();

					miEmpleadoPlanilla.setDni(dni);
					miEmpleadoPlanilla.setNombre(nombre);
					miEmpleadoPlanilla.setApellido(apellido);

					miEmpleadoPlanilla.setFechaNacimiento(fechaNacimiento);

					miEmpleadoPlanilla.setDireccion(direccion);
					miEmpleadoPlanilla.setCiudad(ciudad);

					miEmpleadoPlanilla.setCodEmpleado(codEmpleado);
					miEmpleadoPlanilla.setNumHorasExtras(numHorasExt);
					miEmpleadoPlanilla.setFechaIngreso(fechaIngreso);

					miEmpleadoPlanilla.setArea(area);
					miEmpleadoPlanilla.setCargo(cargo);

					miEmpleadoPlanilla.setSalarioMensual(Double.parseDouble(JOptionPane
							.showInputDialog("Ingrese el salario mensual del empleado por Planilla" + (i + 1))));
					miEmpleadoPlanilla.setPorcentajeAdicional(Double.parseDouble(JOptionPane
							.showInputDialog("Ingrese el salario mensual del empleado por planilla" + (i + 1))));
					listaEmpleadoPlanilla.add(miEmpleadoPlanilla);

				}
				JOptionPane.showMessageDialog(null, "El registro de Empleados por Planilla se ha realizado con exito");

			} else {
				JOptionPane.showMessageDialog(null, "Ya se han registrado los empleados por Planilla", "ERROR",
						JOptionPane.ERROR_MESSAGE);

			}
			break;
		case 2:
			if (listaEmpleadoEventual == null) {
				int

				cantEmpleadoEventual = Integer.parseInt(
						JOptionPane.showInputDialog("Ingrese la cantidad de empleados eventuales a registrar"));
				// listaEmpleadoEventual = new EmpleadoEventual[cantEmpleadoEventual];

				for (int i = 0; i < cantEmpleadoEventual; i++) {
					JOptionPane.showMessageDialog(null, "Registro de datos Empleado Eventual " + (i + 1));

					asignarValoresGeneralesEmpleado("Empleado Eventual " + (i + 1));

					EmpleadoEventual miEmpleadoEventual = new

					EmpleadoEventual();

					miEmpleadoEventual.setDni(dni);
					miEmpleadoEventual.setNombre(nombre);

					miEmpleadoEventual.setApellido(apellido);

					miEmpleadoEventual.setFechaNacimiento(fechaNacimiento);

					miEmpleadoEventual.setDireccion(direccion);
					miEmpleadoEventual.setCiudad(ciudad);

					miEmpleadoEventual.setCodEmpleado(codEmpleado);
					miEmpleadoEventual.setNumHorasExtras(numHorasExt);
					miEmpleadoEventual.setFechaIngreso(fechaIngreso);

					miEmpleadoEventual.setArea(area);
					miEmpleadoEventual.setCargo(cargo);

					miEmpleadoEventual.setHonorariosHora(Double.parseDouble(JOptionPane
							.showInputDialog("Ingrese los honorarios hora del Empleado Eventual " + (i + 1))));
					miEmpleadoEventual.setFechaTerminoContrato(JOptionPane.showInputDialog(
							"Ingrese la fecha de terminacion de contrato del Empleado Eventual" + (i + 1)));
					listaEmpleadoEventual.add(miEmpleadoEventual);

				}
				JOptionPane.showMessageDialog(null, "El registro de Empleados Eventuales se ha realizado con exito");

			} else {
				JOptionPane.showMessageDialog(null, "Ya se han registrado los empleados Eventuales", "ERROR",
						JOptionPane.ERROR_MESSAGE);

			}
			break;

		case 3:
			if (listaMedicos == null) {
				int

				cantMedicos = Integer
						.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de Medicos a registrar"));

				// listaMedicos = new Medico[cantMedicos];
				for (int i = 0; i < cantMedicos; i++) {
					JOptionPane.showMessageDialog(null, "Registro de datos Medico " + (i + 1));
					asignarValoresGeneralesEmpleado("Medico" + (i + 1));

					Medico miMedico = new Medico();
//datos persona
					miMedico.setDni(dni);
					miMedico.setNombre(nombre);
					miMedico.setApellido(apellido);
					miMedico.setFechaNacimiento(fechaNacimiento);
					miMedico.setDireccion(direccion);
					miMedico.setCiudad(ciudad);
//datos empleado
					miMedico.setCodEmpleado(codEmpleado);
					miMedico.setNumHorasExtras(numHorasExt);
					miMedico.setFechaIngreso(fechaIngreso);
					miMedico.setArea(area);
					miMedico.setCargo(cargo);
//datos medico

					miMedico.setEspecialidad(
							JOptionPane.showInputDialog("Ingrese la especialidad del Medico " + (i + 1)));
					miMedico.setNumConsultorio(Integer.parseInt(
							JOptionPane.showInputDialog("Ingrese el numero de consultorio del Medico " + (i + 1))));
					listaMedicos.add(miMedico);
				}
				JOptionPane.showMessageDialog(null, "El registro de Medicos se ha realizado con exito");

			} else {
				JOptionPane.showMessageDialog(null, "Ya se han registrado los Medicos", "ERROR",
						JOptionPane.ERROR_MESSAGE);

			}
			break;
		default:
			break;
		}
	}

	private void asignarValoresGeneralesEmpleado(String tipo) {

		dni = JOptionPane.showInputDialog("Ingrese el DNI del " + tipo);
		nombre = JOptionPane.showInputDialog("Ingrese el nombre del" + tipo);

		apellido = JOptionPane.showInputDialog("Ingrese el apellido del" + tipo);

		fechaNacimiento = JOptionPane.showInputDialog("Ingrese la fecha Nacimiento del " + tipo);

		direccion = JOptionPane.showInputDialog("Ingrese la direccion del" + tipo);

		ciudad = JOptionPane.showInputDialog("Ingrese la ciudad del" + tipo);

		codEmpleado = JOptionPane.showInputDialog("Ingrese el cod Empleado del " + tipo);

		numHorasExt = JOptionPane.showInputDialog("Ingrese el num de Horas Extras del " + tipo);

		fechaIngreso = JOptionPane.showInputDialog("Ingrese la fecha Ingreso del " + tipo);

		area = JOptionPane.showInputDialog("Ingrese el area del " + tipo);
		cargo = JOptionPane.showInputDialog("Ingrese el cargo del" + tipo);
	}
}
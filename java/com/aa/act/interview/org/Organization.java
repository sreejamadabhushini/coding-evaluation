package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

	private Position root;
	static int empId;
	
	public Organization() {
		root = createOrganization();
	}
	
	protected abstract Position createOrganization();
	
	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {
		//your code here
		Employee employee = new Employee(++empId, person);
		Position position = findPosition(root, title,employee);
		return null!=position? Optional.of(position): Optional.empty();
	}

	@Override
	public String toString() {
		return printOrganization(root, "");
	}
	
	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for(Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "\t"));
		}
		return sb.toString();
	}
	
	public Position findPosition(Position position, String title,Employee employee) {

		if (position.getTitle().equals(title)) {
			position.setEmployee(Optional.of(employee));
			return position;
		} else {
			for (Position reportPosition : position.getDirectReports()) {
				Position empPosition = findPosition(reportPosition, title,employee);
				if (null!=empPosition)
					return empPosition;
			}
			return null;
		}
	}
	
	
}

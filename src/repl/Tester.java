/*
 *  Copyright (C) 2010-2018 Buvaneshwaran T
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
 */

package repl;

import java.util.Scanner;

public class Tester {

	public static void main(String args[]) {

		REPL head = new REPL();

		Scanner scanner = new Scanner(System.in);
		System.out.println("enter command");

		while (true) {

			String[] input = scanner.nextLine().split(" ");

			switch (input[0]) {
			case "read":
				System.out.println(head.read(input[1]));
				break;
			case "write":
				head.write(input[1], input[2]);
				break;
			case "delete":
				head.delete(input[1]);
				break;
			case "start":
				head.start();
				break;
			case "abort":
				head.abort();
				break;
			case "commit":
				head.commit();
				break;
			case "quit":
				System.exit(0);

			}
		}
	}
}

package fr.umlv.smoreau.beontime.test;

import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.IOException;

public class Clipboard {
	public static void main(String[] args) {
		Toolkit to = Toolkit.getDefaultToolkit();
		Transferable tr = null;
		Object o = null;
		while (true) {
			tr = to.getSystemClipboard().getContents(null);
			DataFlavor[] dfs = tr.getTransferDataFlavors();
			for (int i = 0; i < dfs.length; ++i) {
				try {
					o = tr.getTransferData(dfs[i]);
					System.out.println("************\n" + dfs[i] + " : \n" + o);
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
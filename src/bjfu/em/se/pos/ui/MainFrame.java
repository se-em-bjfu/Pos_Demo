package bjfu.em.se.pos.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import bjfu.em.se.pos.domain.ProductCatalog;
import bjfu.em.se.pos.domain.Register;
import bjfu.em.se.pos.domain.Store;
import bjfu.em.se.pos.persist.ProductCatalogPersistor;


public class MainFrame extends JFrame {
	private Register register;
	private Store store;
	public MainFrame(Register register,Store store) {
		this.register = register;
		this.store=store;
		initUI();
	}

	private void initUI() {
		setBounds(40,40,500,350);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("超市POS系统");
		getContentPane().setLayout(null);

		JButton btnProcessSale = new JButton("处理新销售");
		btnProcessSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				register.startNewSale();
				SaleProcessingDialog.showDialog(register);
			}
		});
		btnProcessSale.setBounds(61, 62, 150, 83);
		getContentPane().add(btnProcessSale);

		JButton btnShowSales = new JButton("察看销售记录");
		btnShowSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaleListDialog.showDialog(store.getSales());
			}
		});
		btnShowSales.setBounds(277, 62, 173, 83);
		getContentPane().add(btnShowSales);

		JButton btnExit = new JButton("退出程序");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		btnExit.setBounds(365, 237, 113, 27);
		getContentPane().add(btnExit);
	}

	private void closeWindow(){
		dispatchEvent(new WindowEvent(
			this,WindowEvent.WINDOW_CLOSING));
	}

	public static void main(String[] args) {
		ProductCatalog productCatalog=new ProductCatalog();
		productCatalog.initProducts();
		Store store=new Store("学院路美廉美",productCatalog);
		Register register=new Register("1","1号收银台",store,productCatalog);

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "无法设置Windows外观");;
		}
		MainFrame frame=new MainFrame(register,store);
		frame.setVisible(true);
	}
}

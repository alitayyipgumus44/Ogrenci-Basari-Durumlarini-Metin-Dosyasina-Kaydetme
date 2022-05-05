// DERS: BM201 – NESNEYE YÖNELİK PROGRAMLAMA 
// ÖĞRENCİ NUMARASI: 19-1524040062
// ÖĞRENCİ: ALİ TAYYİP GÜMÜŞ

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Final_Odevi 
{
	public static void main(String[] args) throws IOException 
	{
		System.out.printf("			       BM201 - NESNEYE YÖNELİK PROGRAMLAMA DERSİ ÖĞRENCİ İŞLERİ	   		%n%n");
		
		Scanner giris = new Scanner(System.in);
		String harf_notu;
		int sinif_mevcud, m;
		double Başarı_Notu, basari_notu_toplam = 0;
		int vize_toplam = 0, final_toplam = 0, ogrenci = 0;
		
		// (1) Kayıt İşlemleri : Öğrenci Numarası, Adı, Soyadı, Vize Notu; Final Notu
		System.out.printf(" Lütfen BM201 - Nesneye Yönelik Programlama Dersi Sınıf Mevcudunu Giriniz: ");
		sinif_mevcud = giris.nextInt();
		System.out.println();
		
		String [] ogrenci_isim = new String [sinif_mevcud];
		String [] harf_not = new String [sinif_mevcud];
		int [] Vize_Notu = new int [sinif_mevcud];
		int [] Final_Notu = new int [sinif_mevcud];
		double [] basari_not = new double [sinif_mevcud];
		long [] ogrenci_numara = new long [sinif_mevcud];
		
		for(int i=0;i<sinif_mevcud;i++)	
		{	
			System.out.printf(" Lütfen %d.Öğrencinin Okul Numarasını Giriniz: ",i+1);
			long ogrenci_no = giris.nextLong() ;
			ogrenci_numara [i] = ogrenci_no;
			
			String ad_soyad = giris.nextLine() ;
			System.out.printf(" Lütfen %d.Öğrencinin Adı - Soyadını Giriniz: ",i+1);
			ad_soyad = giris.nextLine() ;
			ogrenci_isim [i] = ad_soyad;
		
			System.out.printf(" Lütfen %d.Öğrencinin Vize Notunu Giriniz: ",i+1); 
			Vize_Notu [i] = giris.nextInt();  
			vize_toplam = vize_toplam + Vize_Notu [i]; 
			ogrenci = ogrenci+1;
			
			System.out.printf(" Lütfen %d.Öğrencinin Final Notunu Giriniz: ",i+1);
			Final_Notu [i] = giris.nextInt();
			final_toplam = final_toplam + Final_Notu [i];
			System.out.println();
			
			
				
			// (2) Hesaplama İşlemleri - 1 : Başarı Notu Hesaplama
			Başarı_Notu = (double)Vize_Notu [i]*0.40 + Final_Notu [i]*0.60; 
			
			basari_not [i] = Başarı_Notu;
			
			basari_notu_toplam = basari_notu_toplam + Başarı_Notu;
			
			// (2) Hesaplama İşlemleri - 2 : Harf Notu Belirleme
			if (Başarı_Notu > 75.0) 
			{	
				harf_notu = "A"; 
				harf_not [i] = harf_notu; 	
			}
				
			else if (Başarı_Notu > 50.0 && Başarı_Notu <=75)
			{	harf_notu = "B"; 
				harf_not [i] = harf_notu; 
			}
			else if (Başarı_Notu >25 && Başarı_Notu <=50)
			{	
				harf_notu = "C"; 
				harf_not [i] = harf_notu; 
			}
			else  // (25 >= Başarı_Notu >=0)
			{	
				harf_notu = "D"; 
				harf_not [i] = harf_notu; 
			}
		}
		giris.close();
		
		// KAYIT İŞLEMİ: BM201Kayıt.txt isimli metin dosyasının oluşturulması
		PrintWriter Kayit_Olustur = null;
		File Metin_Dosyasi_Kayit = new File("BM201Kayıt.txt"); 
		try 
		{
			Kayit_Olustur = new PrintWriter(new FileOutputStream(Metin_Dosyasi_Kayit));
			
			for(int z=0; z<sinif_mevcud;z++) 
			{				
				Kayit_Olustur.printf("   %d.KAYIT -> Öğrencinin Numarası: %d    Öğrencinin Adı - Soyadı: %s    "
						+ "Öğrencinin Vize Notu: %d    Öğrencinin Final Notu: %d\n\n"
						,z+1, ogrenci_numara [z], ogrenci_isim [z], Vize_Notu [z], Final_Notu [z]);				
			}
			
			System.out.println(" Kayıtlar Metin Dosyasına Yerleştirildi ✓\n");
			Kayit_Olustur.close();
		}
		
		catch (Exception e) 
		{
			System.out.println(" Kayıt İşlemi Gerçekleştirilirken Hata Oluştu!");
			System.exit(0);
		}
		
		
		//(2) Hesaplama İşlemi - 3 : Girilen Notların Ortalamalarını Hesaplama
		double vize_ortalama = (double)vize_toplam/ogrenci;
		double final_ortalama = (double)final_toplam/ogrenci;
		double basari_notu_ortalama = basari_notu_toplam/ogrenci;
		
		// HESAPLAMA İŞLEMİ: BM201Kayıt.txt'den, BM201Hesap.txt isimli metin dosyasının oluşturulması
		Scanner Dosyayi_Oku = null; 
		PrintWriter Kopyala = null; 
		File Metin_Dosyasi_Hesap = new File("BM201Kayıt.txt"); 

		try 
		{
			Dosyayi_Oku = new Scanner(Metin_Dosyasi_Hesap); 
		} 
		
		catch (FileNotFoundException e) 
		{
			System.out.println("Dosya bulunamadı!");
		}
		
		try 
		{
			Kopyala = new PrintWriter("BM201Hesap.txt");
		} 
		
		catch (FileNotFoundException e) 
		{
			System.out.println("Dosya Oluşturulamadı!");
		}
		
		while(Dosyayi_Oku.hasNext() ) 
		{
			Kopyala.println(Dosyayi_Oku.nextLine());
			
		} 
		
		Kopyala.println();
		Kopyala.println();
		
		for(int z=0; z<sinif_mevcud;z++) 
		{	
			Kopyala.printf("   %d.Öğrencinin Başarı Ortalaması: %.2f   Öğrencinin Harf Notu: %s\n\n",z+1, basari_not [z], harf_not[z]);
		}
		
		Dosyayi_Oku.close(); 
		Kopyala.close(); 

		System.out.println(" Başarı Notu ve Harf Notu Hesaplanarak Metin Dosyasına Yerleştirildi ✓\n");
			
		// RAPORLAMA İŞLEMİ: BM201Hesap.txt'den BM201Rapor.txt isimli metin dosyasının oluşturulması
		Scanner Dosyayi_Oku_1 = null; 
		PrintWriter Kopyala_1 = null; 
		File Metin_Dosyasi_Rapor = new File("BM201Hesap.txt"); 

		try 
		{
			Dosyayi_Oku_1 = new Scanner(Metin_Dosyasi_Rapor); 
		} 
		
		catch (FileNotFoundException e) 
		{
			System.out.println("Dosya bulunamadı!");
		}
		
		try 
		{
			Kopyala_1 = new PrintWriter("BM201Rapor.txt");
		} 
		
		catch (FileNotFoundException e) 
		{
			System.out.println("Dosya Oluşturulamadı!");
		}
		
		
		while(Dosyayi_Oku_1.hasNext()) 
		{
				Kopyala_1.println(Dosyayi_Oku_1.nextLine());	
		}
		
		Kopyala_1.println();
		Kopyala_1.println();
		
		Kopyala_1.printf("   Sınıfın Vize Notlarının Ortalaması: %.2f       Sınıfın Final Notlarının Ortalaması: %.2f       "
				+ "Sınıfın Başarı Notlarının Ortalaması: %.2f%n%n" ,vize_ortalama, final_ortalama, basari_notu_ortalama);
		

		Dosyayi_Oku_1.close(); 
		Kopyala_1.close(); 

		System.out.println(" Sınıfın Vize, Final ve Başarı Ortalaması Hesaplanarak Metin Dosyasına Yerleştirildi ✓\n");
				
		// RAPORLAMA İŞLEMİ: BM201Rapor.txt'den BM201Cikti.txt isimli metin dosyasının oluşturulması
		Scanner Dosyayi_Oku_2 = null; 
		PrintWriter Kopyala_2 = null; 
		File Metin_Dosyasi_Cikti = new File("BM201Rapor.txt"); 

		try 
		{
			Dosyayi_Oku_2 = new Scanner(Metin_Dosyasi_Cikti); 
		} 
		
		catch (FileNotFoundException e) 
		{
			System.out.println("Dosya bulunamadı!");
		}
		
		try 
		{
			Kopyala_2 = new PrintWriter("BM201Cikti.txt");
		} 
		
		catch (FileNotFoundException e) 
		{
			System.out.println("Dosya Oluşturulamadı!");
		}
		
		
		while(Dosyayi_Oku_2.hasNext()) 
		{
			Kopyala_2.println(Dosyayi_Oku_2.nextLine());
				
		}
		
		Kopyala_2.println();
		Kopyala_2.println();
		
		Kopyala_2.printf("   Harf Notları İçin Frekans Grafiği;\n\n");
		
		Kopyala_2.printf("   A: ");
		
		// (2) Hesaplama İşlemleri - 4 : Tüm Kayıtlar İçin Harf Notlarının Frekans Grafiğinin Oluşturulması
		for(m=0;m<sinif_mevcud;m++)
		{
			if (basari_not[m] > 75.0)
				Kopyala_2.print("* ");
		}
		
		Kopyala_2.println();
		Kopyala_2.printf("   B: ");	
		for(m=0;m<sinif_mevcud;m++)
		{
			if (basari_not[m] > 50.0 && basari_not[m] <=75) 
				Kopyala_2.print("* ");
		}
		
		Kopyala_2.println();
		Kopyala_2.printf("   C: ");
		for(m=0;m<sinif_mevcud;m++)
		{
			if (basari_not[m] >25 && basari_not[m] <=50) 
				Kopyala_2.print("* ");
		}
		
		Kopyala_2.println();
		Kopyala_2.printf("   D: ");
		for(m=0;m<sinif_mevcud;m++)
		{
			if (basari_not[m] >= 0 && basari_not[m] <=25) 
				Kopyala_2.print("* ");
		}	
		
		Dosyayi_Oku_2.close(); 
		Kopyala_2.close(); 

		System.out.println(" Harf Notu Frekans Grafiği Metin Dosyasına Yerleştirildi ✓");
	}
}
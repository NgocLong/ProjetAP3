import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**Class chức năng chỉnh độ sáng tối hình ảnh */
public class colorUpDown {
	
	/**Hàm dựng (rỗng) */
	colorUpDown(){
	}

	/**Hàm chỉnh độ tối */
	public void setColorDown(BufferedImage img, int value_color) throws IOException
	{
		// lấy các điểm màu của hình ảnh gốc
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int rgba = img.getRGB(x, y);
                Color col = new Color(rgba, true);
                // giảm mức màu của hình ảnh xuống
                col = new Color(col.getRed() - col.getRed()/value_color,
                                col.getGreen() - col.getGreen()/value_color,
                                col.getBlue() - col.getBlue()/value_color);
                // gán màu đã giảm cho hình ảnh 
                img.setRGB(x, y, col.getRGB());                
            }
        }      
	}
	
	/**Hàm chỉnh độ sáng */
	public void setColorUp(BufferedImage img, int value_color) throws IOException
	{
		// lấy các điểm màu của hình ảnh gốc
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int rgba = img.getRGB(x, y);
                // tăng mức màu của hình ảnh lên, có xét điều kiện để màu tăng không
                // vượt quá MAX = 255
                Color col = new Color(rgba, true);                
                int red = col.getRed() + col.getRed()/value_color;; 
                int green = col.getGreen() + col.getGreen()/value_color;
                int blue = col.getBlue() + col.getBlue()/value_color;          
                if(red > 255)
                	red = col.getRed();
                if(green > 255)
                	green = col.getGreen();
                if(blue > 255)
                	blue = col.getBlue();
                col = new Color(red,green,blue);
                // gán màu đã tăng cho hình ảnh
                img.setRGB(x, y, col.getRGB());                
            }
        }      
	}
}

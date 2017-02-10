package scondor.font.mesh;

/**
 * Stores the vertex data for all the quads on which a text will be rendered.
 * 
 * @author Karl
 *
 */
public class TextMeshData {

	private float[] vertexPositions;
	private float[] textureCoords;
	private float[] buffer = new float[2];
	private int left, right;

	protected TextMeshData(float[] vertexPositions, float[] textureCoords) {
		reverse(vertexPositions);
		reverse(textureCoords);
		this.vertexPositions = vertexPositions;
		this.textureCoords = textureCoords;
	}

	public float[] getVertexPositions() {
		return vertexPositions;
	}

	public float[] getTextureCoords() {
		return textureCoords;
	}

	public int getVertexCount() {
		return vertexPositions.length / 2;
	}

	public void reverse(float[] data) {
		left = 0;
		right = data.length - 2;

		while (left < right) {
			buffer[0] = data[left];
			buffer[1] = data[left+1];
			
			data[left] = data[right];
			data[left+1] = data[right+1];
			
			data[right] = buffer[0];
			data[right+1] = buffer[1];
			
			left+=2;
			right-=2;
		}
	}

}

#version 330

in vec2 position;
in vec2 tex_coords;

out vec2 coords;

uniform vec2 translation;

void main(void){

	gl_Position = vec4(translation + position,0.0,1.0);
	coords = tex_coords;

}
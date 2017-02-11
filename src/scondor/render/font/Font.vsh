#version 330

in vec2 position;
in vec2 tex_coords;

out vec2 coords;

uniform vec2 translation;

const int PRIORITIES = 5;
uniform float layer;
uniform int priority;

void main(void){

	float z = ((1.0/PRIORITIES)*priority)+(layer/PRIORITIES);

	gl_Position = vec4(translation + position,z,1.0);
	coords = tex_coords;

}
#version 400 core

in vec3 position;

out vec2 tex_coords;

uniform mat4 TM;
uniform float rows;
uniform float columns;
uniform vec2 offset;

const int PRIORITIES = 5;
uniform float layer;
uniform int priority;

void main(void) {

	float z = ((1.0/PRIORITIES)*priority)+(layer/PRIORITIES);

	gl_Position = TM * vec4(position.xy, z , 1.0);
	
	tex_coords = (vec2(((position.x+1.0)/2.0)/columns, (1 - (position.y+1.0)/2.0)/rows)) + offset;
	
}